# Discord DMer Bot
<img src="https://i.imgur.com/kQsmXcp.png" alt="isolated" width="200"/>\
This is a simple Discord bot script written in Java that allows you to send direct messages (DMs) to all members of a specific guild (server) on Discord. It uses the JDA (Java Discord API) library for Discord interaction.

## Prerequisites

Before using this bot, ensure you have the following:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) version 17 or above
- A Discord bot token (you can create one on the [Discord Developer Portal](https://discord.com/developers/applications))

## Usage
### Build Yourself
1. Clone the repository to your local machine.

2. Compile and run the bot using the following commands:

   ```shell
   gradle build
   java -jar build/libs/ADBOT-1.0.jar
   ```
3. Run the `ADBOT-1.0.jar`
   
4. The bot will prompt you to enter your Discord bot token. Provide your token to proceed.

5. After successfully logging in, you can now send DMs to all members of a guild (server) by enter the guild ID when prompted. The bot will load all guild members and send the specified message to each member.

6. The bot will create a private channel with each user and send the specified message. The status of each message will be displayed in the console.
### Use Release
1. Go the the [latest release page](https://github.com/RTX4O9O/DiscordDMer/releases/latest) and download `pack.zip` (`pack.zip` included `DMer-1.0.jar` and `start.bat`)
   
2. Unzip the `pack.zip`
   
3. Run the `start.bat`, and it should automaticly run
   
4. The bot will prompt you to enter your Discord bot token. Provide your token to proceed.
   
5. After successfully logging in, you can now send DMs to all members of a guild (server) by enter the guild ID when prompted. The bot will load all guild members and send the specified message to each member.
    
6. The bot will create a private channel with each user and send the specified message. The status of each message will be displayed in the console.
## Disclaimer

Be aware that messaging or spamming users on Discord may violate Discord's Terms of Service. Use this bot responsibly and in compliance with Discord's guidelines. The developer is not responsible for any misuse or consequences of using this bot.
