package me.rtx4090;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Result;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main extends ListenerAdapter {
    private JDA jda;

    public Main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Discord Bot Token: ");
        String botToken = scanner.nextLine();
        jda = JDABuilder.createDefault(botToken)
                .addEventListeners(this)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();
        while (true) {
            try{
                jda.awaitReady();
                sendPrivateMessage();
                //dm();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    private void sendPrivateMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Guild ID: ");
        String guildId = scanner.nextLine();
        System.out.println();
        spamAllGuildMembers(guildId);
    }

    private void spamAllGuildMembers(String guildID) {
        Guild guild = jda.getGuildById(guildID);
        if (guild == null) return;

        List<Member> members = guild.loadMembers().get();
        for (Member member : members) {
            User user = member.getUser();
            spamUserPrivateChannel(user);
        }
    }

    private void spamUserPrivateChannel(User user) {
        if (user.isBot()) return;

        Result<PrivateChannel> result = jda.openPrivateChannelById(user.getId()).mapToResult().completeAfter(1, TimeUnit.SECONDS);
        // waits for channel creation, and timeout after 1 second

        if (result.isFailure()) {
            System.out.println("Channel creation for %#s failed!");
            result.getFailure();
            return;
        }

        PrivateChannel channel = result.get();
        Result<Message> sentMessage = channel.sendMessage("abcabcabc").mapToResult().completeAfter(1, TimeUnit.SECONDS);
        // waits for message to send, and timeout after 1 second
        if (sentMessage.isSuccess()) {
            System.out.printf("Message has been sent to %#s successfully\n", user);
        } else {
            System.out.printf("Message failed to sent to %#s\n", user);
            System.out.println(sentMessage.getFailure().toString());
        }
    }
    private void dm(Scanner scanner) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("User ID: ");
        String recipientUserId = scanner.nextLine();
        System.out.print("Message: ");
        String messageContent = scanner.nextLine();

        Result<PrivateChannel> result = jda.openPrivateChannelById(recipientUserId).mapToResult().completeAfter(1, TimeUnit.SECONDS);
        // waits for channel creation, and timeout after 1 second
        if (result.isFailure()) {
            System.out.println("Channel creation for %#s failed!");
            return;
        }

        PrivateChannel channel = result.get();

        User user = channel.getUser();
        Result<Message> sentMessageResult = channel.sendMessage(messageContent).mapToResult().completeAfter(1, TimeUnit.SECONDS);
        // waits for message to send, and timeout after 1 second
        if (sentMessageResult.isSuccess()) {
            System.out.printf("Message has been sent to %#s successfully\n", user);
        } else {
            System.out.printf("Message failed to sent to %#s\n", user);
            sentMessageResult.getFailure().printStackTrace();
        }
    }
}