# The Outer Rim Console Test Report

Tested command: `java -cp out TheOuterRimGame`

Date tested: 2026-06-22

## Coverage summary

I tested the game through interactive terminal sessions and source-guided path checks. The main playable systems are present, but the overall game loop is broken: the player can enter one subsystem, but several "Return to Bridge" paths simply return out of the only active bridge call, which ends the Java process. Because of this, the intended loop of buy cargo -> travel -> sell cargo -> upgrade -> travel farther is not practically playable.

## Complete gameplay tree

```text
Pre-game menu
├── [1] Start Adventure
│   ├── Enter player name
│   ├── Story intro pauses
│   ├── Starter ship reveal
│   ├── Starting planet: Earth
│   └── Bridge Console
│       ├── [1] Ship Console
│       │   ├── [1] View ship info
│       │   │   └── Shows fuel, speed, cargo capacity, upgrade levels
│       │   ├── [2] View cargo
│       │   │   └── Shows empty cargo or item stacks
│       │   ├── [3] Refuel
│       │   │   ├── Full tank: "Fuel tank is already full."
│       │   │   ├── Enough credits: refuels missing kL for 5 credits each
│       │   │   └── Not enough credits: prints "Not enough credits."
│       │   ├── [4] Upgrade ship
│       │   │   ├── [1] Fuel Upgrade
│       │   │   │   ├── Level 0 cost: 700, max fuel +20
│       │   │   │   ├── Level 1 cost: 1600, max fuel +20
│       │   │   │   └── Max level: cost 0, fully upgraded message
│       │   │   ├── [2] Speed Upgrade
│       │   │   │   ├── Level 0 cost: 600, speed +1
│       │   │   │   ├── Level 1 cost: 1500, speed +1
│       │   │   │   └── Max level: cost 0, fully upgraded message
│       │   │   ├── [3] Cargo Upgrade
│       │   │   │   ├── Level 0 cost: 500, capacity +10
│       │   │   │   ├── Level 1 cost: 1400, capacity +10
│       │   │   │   └── Max level: cost 0, fully upgraded message
│       │   │   ├── [0] Return to Ship Console
│       │   │   └── Invalid number: currently exits silently
│       │   ├── [0] Return to Bridge
│       │   └── Invalid number: invalid message, stays in Ship Console
│       ├── [2] Market
│       │   ├── Shows current planet stock and buy prices
│       │   ├── [1] Buy item
│       │   │   ├── Invalid item number
│       │   │   ├── Invalid quantity <= 0
│       │   │   ├── Not enough market stock
│       │   │   ├── Not enough cargo space
│       │   │   ├── Not enough credits
│       │   │   └── Successful buy: credits decrease, market stock decreases, cargo gains stack
│       │   ├── [2] Sell item
│       │   │   ├── No cargo
│       │   │   ├── Invalid cargo selection
│       │   │   ├── Invalid quantity
│       │   │   └── Successful sell: credits increase, cargo quantity decreases/removes
│       │   ├── [0] Return to Bridge
│       │   │   └── Currently exits the game instead
│       │   └── Invalid number: invalid message, stays in Market
│       ├── [3] Travel
│       │   ├── Shows all planets, including current planet
│       │   ├── Current planet selection: "You are already on..."
│       │   ├── Locked by fuel system level
│       │   │   ├── Vesta Trade Outpost requires fuel upgrade level 1
│       │   │   └── Helios/Erebus require fuel upgrade level 2
│       │   ├── Not enough fuel
│       │   ├── Successful travel
│       │   │   ├── Fuel decreases by distance
│       │   │   ├── Travel delay prints one dot per AU / speed
│       │   │   ├── Arrival message
│       │   │   ├── Milestone text may print +reputation
│       │   │   └── New Galactic News generated
│       │   ├── [0] Return to Bridge
│       │   │   └── Currently exits the game instead
│       │   └── Invalid number above planet count: currently exits silently
│       ├── [4] Galactic News
│       │   ├── Shows current headline
│       │   └── Press any key currently exits the game instead of returning to Bridge
│       ├── [0] Quit
│       └── Invalid number: invalid message, but then returns out of the only bridge call
├── [2] Tips & Tricks
│   └── Shows tips, then returns to pre-game menu in a normal terminal
├── [3] Author's Note / Credits
│   └── Shows credits, then returns to pre-game menu in a normal terminal
├── [0] Quit
└── Invalid number
```

## Tested scenarios

- Pre-game quit: entered `0`, game printed goodbye and exited.
- Pre-game tips with piped input: `printf '2\n\n0\n' | java -cp out TheOuterRimGame` crashed with `No line found`.
- Start adventure: entered name, passed intro/story pauses, reached Bridge Console.
- Galactic News: selected `[4]`, viewed headline, pressed Enter. The process exited instead of returning to Bridge.
- Market:
  - Invalid item number: buy item `9` printed `Invalid item number.`
  - Sell with no cargo: printed `You have no cargo to sell.`
  - Stock limit: tried to buy 13 Food while Earth had 12, received `Not enough stock...`
  - Successful buy: bought 12 Food for 180 credits; credits became 820 and Earth Food stock became 0.
  - Cargo capacity: with 12 Food in cargo, tried to buy 9 Fuel and got `Not enough cargo space...`
  - Invalid sell quantity: tried to sell 13 Food while holding 12, got `Invalid quantity.`
  - Successful sell: sold 12 Food for 144 credits; credits became 964.
  - Market `[0] Return to Bridge`: process exited instead of returning.
- Travel:
  - Current planet: selecting Earth while on Earth printed `You are already on Earth.`
  - Locked destination: selecting Vesta from Earth printed `Vesta Trade Outpost requires a stronger fuel system.`
  - Successful travel: Earth -> Luna consumed 5 fuel and printed `New planet visited!` / `+25 reputation`.
  - Successful travel: Luna -> Caelum consumed 5 fuel and printed `New planet visited!` / `+25 reputation`.
  - Successful travel: Caelum -> Earth consumed 10 fuel, leaving 0 fuel.
  - Not enough fuel: Earth -> Luna with 0 fuel printed `Not enough fuel. You need 5kL for this trip.`
  - Invalid destination `7`: process exited silently.
- Ship Console:
  - View ship info showed starter stats: 20/20 fuel, speed 1, cargo capacity 20.
  - View cargo showed `- Empty`.
  - Refuel while full printed `Fuel tank is already full.`
  - Cargo upgrade purchased successfully for 500 credits; capacity became 30.
  - Fuel upgrade after spending credits printed `Not enough credits.`
  - Invalid upgrade input `9`: process exited silently.

## Bugs and issues

### 1. Critical: Bridge menu is not a real loop

Evidence:
- `gameLoopBridge()` displays the menu once and uses a `switch`, but it does not loop after a submenu returns.
- `marketConsole()` case `[0]` just `return`s.
- `travelConsole()` case `[0]` just `return`s.
- `newsConsole()` just `return`s after the pause.

Observed result:
- Returning from Market, Travel, or Galactic News ends the Java process.

Impact:
- The core game loop is broken. A player cannot reliably buy goods, return to Bridge, travel, sell goods, upgrade, and continue.

Suggested fix:
- Make `gameLoopBridge()` a `while (true)` loop and have submenus return normally to that loop.
- Avoid calling `gameLoopBridge()` from inside `shipConsole()`; just return to the caller.

### 2. Critical: Reputation milestones are printed but never applied to the player

Evidence:
- `ReputationMilestones.checkPlanetDiscovery(...)`, `recordCreditsEarned(...)`, and `recordTradeRoute()` return reputation amounts.
- `TheOuterRimGame` calls these methods but ignores their return values.
- `addReputation(int amount)` exists in `TheOuterRimGame`, but no tested milestone path calls it.

Observed result:
- Travel to Luna and Caelum printed `+25 reputation`, but no actual reputation update was shown.

Impact:
- Player reputation stays at 0.
- Reputation titles cannot progress.
- The victory check in `addReputation()` is unreachable.

Suggested fix:
- Wrap each milestone call with `addReputation(...)`, for example:

```java
addReputation(milestones.checkPlanetDiscovery(currentPlanet));
addReputation(milestones.recordCreditsEarned(sellValue));
addReputation(milestones.recordTradeRoute());
```

### 3. Critical: Invalid Travel and Upgrade choices can silently exit the game

Evidence:
- `travelConsole()` only handles `0` and valid planet numbers. There is no final `else` for invalid positive or negative values.
- `upgradeConsole()` handles choices `0` through `3`, but has no invalid-choice branch.

Observed result:
- Entering `7` in Travel exited the process with no message.
- Entering `9` in Ship Upgrades exited the process with no message.

Impact:
- A simple typo can end the game with no explanation.

Suggested fix:
- Add invalid-choice handling that prints a message, pauses, and redisplays the same menu.

### 4. High: Recursive menus can eventually cause stack growth problems

Evidence:
- `shipConsole()`, `marketConsole()`, `travelConsole()`, and `upgradeConsole()` call themselves after most actions.
- `shipConsole()` also calls `gameLoopBridge()` when returning to Bridge.

Impact:
- Long play sessions can build deep call stacks.
- Control flow becomes hard to reason about and directly caused several return-path bugs.

Suggested fix:
- Convert each menu to a `while` loop.
- Use `return` only to leave a submenu.

### 5. High: Piped or automated input crashes because two Scanners share `System.in`

Evidence:
- `TheOuterRimGame` creates `new Scanner(System.in)` for menu input.
- `ConsoleUI.ui` also creates a static `new Scanner(System.in)` for `pressAnyKey()`.

Observed result:
- `printf '2\n\n0\n' | java -cp out TheOuterRimGame` reached the tips pause and then ended setup with `An error occurred during game setup: No line found`.
- Similar piped setup runs failed during story pauses.

Impact:
- Automated testing is unreliable.
- Fast/piped input can be consumed by the wrong scanner.

Suggested fix:
- Use one shared `Scanner` for all console input, passed into UI helpers or owned centrally by the game.

### 6. Medium: Sound errors print constantly during normal play

Observed result:
- Every run repeatedly printed errors like:

```text
Error playing sound: No line matching interface Clip supporting format PCM_SIGNED unknown sample rate...
```

Impact:
- The error text clutters the console and makes the UI hard to read.
- This may depend on the local audio system, but the game currently exposes every audio failure to the player.

Suggested fix:
- Add a way to disable sound in console/test mode.
- Fail silently or print one warning once instead of repeating the full error on every sound call.

### 7. Medium: Market item numbering conflicts with action numbering

Observed result:
- Market stock is displayed as `[1] Food`, `[2] Medicine`, etc.
- Immediately below, actions are also displayed as `[1] Buy item`, `[2] Sell item`.

Impact:
- The screen is confusing because `[1]` means both "Food" and "Buy item" depending on which prompt the user is answering.

Suggested fix:
- Use action labels like `[B] Buy`, `[S] Sell`, `[0] Return`, or separate the item list from command prompts more clearly.

### 8. Medium: Fuel is sold as cargo but cannot be used to refuel

Observed result:
- Earth market sells `Fuel`, and buying Fuel adds it to cargo.
- Refueling uses credits directly and ignores Fuel cargo.

Impact:
- Players may reasonably expect Fuel cargo to refill the ship, but it behaves only as a trade item.

Suggested fix:
- Rename cargo fuel to `Fuel Canister` and explain it is trade cargo, or add a "Use fuel canister" action.

### 9. Medium: Progression to distant markets is economically and structurally blocked

Evidence:
- Vesta requires fuel upgrade level 1.
- Helios and Erebus require fuel upgrade level 2.
- Fuel upgrades cost 700 and 1600.
- The broken Bridge loop prevents a normal buy/travel/sell/upgrade cycle.
- Reputation rewards are not applied.

Impact:
- Even if individual travel locks work, the larger progression path cannot be completed normally.

Suggested fix:
- Fix the main loop first, then retest economy balance and upgrade pacing.

### 10. Low: Travel menu lists the current planet as a selectable destination

Observed result:
- While on Earth, the numbered list includes `[1] Travel to Earth | Distance: 0 AU`.
- Selecting it prints `You are already on Earth.`

Impact:
- Not a crash, but it adds unnecessary clutter.

Suggested fix:
- Either omit the current planet from the numbered list or display it as disabled/current.

### 11. Low: Long forced delays make testing and repeated play slow

Observed result:
- Setup and travel contain multiple `Thread.sleep` delays.
- Travel prints one dot per AU, so long routes can take a long time unless speed is upgraded.

Impact:
- This slows automated and manual testing.

Suggested fix:
- Add a debug/test flag to skip delays, or keep delays short during development.

## Priority fix order

1. Convert `gameLoopBridge()` into a persistent loop and cleanly return from submenus.
2. Apply reputation milestone return values through `addReputation(...)`.
3. Add invalid-choice handling to Travel and Upgrade menus.
4. Replace recursive menu calls with loops.
5. Consolidate console input into one shared `Scanner`.
6. Add sound-disable behavior or one-time sound warnings.
7. Retest economy balance after the core loop and reputation bugs are fixed.
