package me.rtx4090;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Scanner;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Result;

public class Main extends ListenerAdapter {

    private JDA jda;
    public String messageContent;
    public static String botToken;

    public Main() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Discord Bot Token: ");
        botToken = scanner.nextLine();

        JDABuilder builder = JDABuilder.createDefault(botToken)
                .addEventListeners(this)
                .enableIntents(GatewayIntent.GUILD_MEMBERS);

        try {
            jda = builder.build();
            jda.awaitReady();
            setInvisibleStatus();
            sendPrivateMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    private void sendPrivateMessage() {
        System.out.println("Guilds that the bot is currently in");
        System.out.println();
        for (Guild guild : jda.getGuilds()) {
            System.out.println("Guild Name: " + guild.getName());
            System.out.println("Guild ID: " + guild.getId());
            System.out.println("--------------------------------------");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Guild ID: ");
        String guildId = scanner.nextLine();
        System.out.println();
        spamAllGuildMembers(guildId);
        sendPrivateMessage();
    }

    private void spamAllGuildMembers(String guildID) {
        Guild guild = jda.getGuildById(guildID);
        if (guild == null) return;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Message: ");
        messageContent = scanner.nextLine();

        List<Member> members = guild.loadMembers().get();
        for (Member member : members) {
            User user = member.getUser();
            spamUserPrivateChannel(user);
        }
    }

    private void spamUserPrivateChannel(User user) {
        if (user.isBot()) return;

        Result<PrivateChannel> result = jda.openPrivateChannelById(user.getId()).mapToResult().completeAfter(1, TimeUnit.SECONDS);

        if (result.isFailure()) {
            System.out.println("Channel creation for " + user.getAsTag() + " failed!");
            result.getFailure();
            return;
        }

        PrivateChannel channel = result.get();
        Result<Message> sentMessage = channel.sendMessage(messageContent).mapToResult().completeAfter(1, TimeUnit.SECONDS);

        if (sentMessage.isSuccess()) {
            System.out.printf("Message has been sent to %s successfully\n", user.getAsTag());
        } else {
            System.out.printf("Message failed to send to %s\n", user.getAsTag());
            System.out.println(sentMessage.getFailure().toString());
        }
    }

    private void setInvisibleStatus() {
        jda.getPresence().setStatus(OnlineStatus.INVISIBLE);
    }
}
