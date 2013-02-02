# [![JSONAPI-Commands](http://i.imgur.com/CtpZF.png)](http://dev.bukkit.org/server-mods/jsonapi-commands/)

**With JSONAPI-Commands you can easily register** ***real***
**ingame commands like `/spawn` or `/tp silvinci Notch`.**  
This is it. No downsides. No quirky hacks. It just works.
You don't even get these pesky `Unknown command` messages.

View the [BukkitDev page](http://dev.bukkit.org/server-mods/jsonapi-commands/).

## Installation

Download the [latest version](https://dl.dropbox.com/s/0pt7rk39y3qgmxt/JSONAPI-Commands.jar?dl=1)
of JSONAPI-Commands and put it in your server's `plugins` directory.
If you haven't already grab the [latest version](http://ci.alecgorge.com/job/JSONAPI/lastSuccessfulBuild/artifact/target/JSONAPI.jar)
of JSONAPI and put in the very same directory.

## How it works

JSONAPI-Commands listens on the [`PlayerCommandPreprocessEvent`](http://jd.bukkit.org/doxygen/d4/dbe/classorg_1_1bukkit_1_1event_1_1player_1_1PlayerCommandPreprocessEvent.html), that is fired whenever a player (it has to be a player, console doesn't count) enters a command. As the name suggests it is called *before* any other plugin or Bukkit itself can interfere. This way we can [cancel](http://jd.bukkit.org/doxygen/d9/d37/interfaceorg_1_1bukkit_1_1event_1_1Cancellable.html#ac672af0d6b82d1598c02c8f81b4e06a8) the whole command and noone will ever notice, that in reality the issued command never was officially registered.

## Configuration

JSONAPI-Commands creates a `commands.yml` in the `JSONAPI` folder. This file is pretty self-explanatory.
It contains all registered commands and may be edited by hand. This file survives server reloads and restarts.
This way you're programs don't have to re-register (refer to 'Methods') their commands all the time,
nevertheless I do encourage you to implement checks into your software. You'll never now what can possibly happen.

## How to use it

JSONAPI-Commands introduces new methods and a stream to @alecgorge's *great* [JSONAPI](https://github.com/alecgorge/jsonapi).

### Methods

- `commands.addListener(String command)` registers the given command
- `commands.addListeners(String[] command)` registers the given commands
- `commands.removeListener(String command)` removes the given command
- `commands.removeListeners(String[] command)` removes the given commands
- `commands.removeAllListeners()` removes all listeners *(use with caution!)*
- `commands.allListeners()` returns an array of all registered commands

### Stream

The new stream is named `commands`. This means you can subscribe to it with this URL:
```
/api/2/subscribe?json={"name":"commands","username":"yourName","key":sha256("yourName"+"commands"+"yourPassword"+"yourSalt")}
```
A sample (prettifyed) response for `/tp silvinci Notch` run by the player alecgorge may look like this:
```
{
  "result": "success",
  "source": "commands",
  "success": {
    "time": 1353715957,
    "command": "tp",
    "args": ["silvinci", "Notch"],
    "player": "alecgorge"
  }
}
```

- `time` is the exact timestamp when the command was issued
- `command` is the command issued
- `args` is an array of the arguments concatenated to the command *(no args = empty array)*
- `player` is the player who issued the command *(not the affected player)*

## Examples

... will follow shortly.

## Help

- If you encounter a bug or have a feature request, feel free to [open an issue](https://github.com/CubixCraft/jsonapi-commands/issues/new).
- If you can't get everything up and running and need a helping hand, you can [post to the forum thread](http://forums.bukkit.org/threads/admn-info-jsonapi-v4-0-1-json-http-and-socket-api-for-controlling-a-server-1-4.14270/).

## License

(The MIT License)

Copyright (c) 2012 Jan Buschtöns &lt;buschtoens@gmail.com&gt;

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
'Software'), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
