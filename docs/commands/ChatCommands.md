# Chat Commands [cmd.chat]

Gac14 Provides many ways to interact with various chat channels

## Channels [cmd.chat.channels]

The following Channels are provided by default in gac14

1. gac14:global -> Main Chat
2. gac14:admin -> Admin Only Chat
3. gac14:private/<user> -> Private Message to <user>, given by UUID.
4. gac14:faction/<faction> -> Faction Chat for <faction>, given by UUID.
5. gac14:faction/<faction>/<role> -> Faction Chat members of <faction> with at least <role>.
6. gac14:faction/<faction>/ally -> Faction Chat for Members of <faction> and of the ally of <faction>
7. gac14:faction/<faction>/ally/<role> -> Faction Chat for Members of <faction> and of the ally of <faction>, with at least <role>.
8. gac14:null -> Null Channel. By default, no one can speak in this channel and no one listens to it.
9. gac14:book -> Channel for signs and books. By default, everyone can speak in this channel, but no one listens to it. This channel cannot be the target of the say ... in command, or the channel command. If a message is sent to it, that message is ignored, unless that message is stored in a written book or on a sign.  

## Filters [cmd.chat.filters]

The following filters can be applied to chat channels (and most are be default)

1. gac14:profanity -> Filters based on the Sentry Server Word Blacklist. No exemptions
2. gac14:ipaddress -> Filters out INetv4 and INetv6 Addresses. 
3. gac14:website -> Filters out websites. Only applies if it forms a valid URL with a valid TLD.

## Commands [cmd.chat.list]

```
/say <message> //(1)
/say <message> in <channel> //(2)
/channel <channel> //(3)
/chat players <player> set prefix|suffix to <text> //(4)
/chat players <player> set prefix|suffix to <text> in <channel>|* //(5)
/chat players <player> clear prefix|suffix //(6)
/chat players <player> clear prefix|suffix in <channel>|* //(7)
/chat players <player> mute|unmute|deafen|undeafen //(8)
/chat players <player> mute|unmute|deafen|undeafen in <channel>|* //(9)
/chat channels <channel> set prefix|suffix to <text> //(10)
/chat channels <channel> clear prefix|suffix //(11)
/chat channels <channel> remove|add listener|member <player> //(12)
/chat channels <channel> disable //(13)
```

1. Says `<message>` as though it was typed literally in chat, except target selectors are expanded to a list of player names for each player matched by the selector and entity UUIDs for each non-player entity matched by the selector. If the command is run in an elevated context, ignores if the sender is muted or the channel is disabled. If @s appears in `<message>` and the sender is the server, @s expands as the word "root". If the 2nd last word is in, then the last word must not be a valid resource location or parses as (2).
2. Says a message in channel. Acts as though /channel `<channel>`, followed by /say `<message>`.
3. Sets the channel which the senders messages are sent when typed in chat or via form (1) of the say command. The sender must be a member of `<channel>` or the command must be run in an elevated context. Otherwise the command fails. 
4. Sets the global prefix/suffix. Requires `chat.misc.command` permission. 
5. If `<channel>` is the name of a channel, sets the prefix/suffix in that channel. Otherwise, if `<channel>` is `*`, treats as (4), except that if the prefix/suffix is already set in a channel, then overrides the channel specific prefix/suffix. This sets the global prefix/suffix for the player, and all channel specific prefix/suffixes.
6. Unsets the global prefix/suffix for a player. In channels where a channel specific prefix/suffix is set, the prefix/suffix is not affected.
7. Unsets the channel specific prefix/suffix for `<player>` in `channel`. If `<channel>` is `*`, this unsets all channel specific prefix/suffixes, and the global prefix/suffix.
8. mutes|unmutes the player in all channels that they are able to listen in. If the player is unable to be muted in a channel, the request is ignored silently for that channel only, unless the command is run in an elevated context. This command *synchronizes-with* other invocations of this command, and any attempts by *player* to send a message in any channel. If a player is specifically muted in a channel, that will not be affected by  *unmute*. 
9. mutes|unmutes the player in a specific channel. If `<channel>` is `*`, then treats as (8), except that *unmute* clears all mutes, even on channels which the player is explicitly muted on. This command *synchronizes-with* other invocations of this command on which affect the same channel (either they are both `<channel>` or one is a channel and the other is `*`), and attempts by *player* to send a message on `<channel>`. Additionally, if `<channel>` is `*`, this *synchronizes-with* invocations of (8). 