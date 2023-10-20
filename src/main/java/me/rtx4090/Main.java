package me.rtx4090;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.User;
// import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main extends ListenerAdapter {
    private JDA jda;

    public Main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Discord Bot Token: ");
        String botToken = scanner.nextLine();
        jda = JDABuilder.createDefault(botToken).addEventListeners(this).build();
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        System.out.println("All members in the guild " + event.getGuild().getName() + " have been downloaded.");
        sendPrivateMessage();
    }

    private void sendPrivateMessage() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            loop(scanner);
        }
    }




    private void loop(Scanner scanner) {
        System.out.print("User ID: ");
        String recipientUserId = scanner.nextLine();
        System.out.print("Message: ");
        String messageContent = scanner.nextLine();

        PrivateChannel channel = jda.openPrivateChannelById(recipientUserId).completeAfter(1, TimeUnit.SECONDS);
        // waits for channel creation, and timeout after 1 second
        if (channel != null) {

            User user = channel.getUser();
            Message sentMessage = channel.sendMessage(messageContent).completeAfter(1, TimeUnit.SECONDS);
            // waits for message to send, and timeout after 1 second
            if (sentMessage == null) {
                System.out.printf("Message failed to sent to %#s\n", user);
            } else {
                System.out.printf("Message has been sent to %#s successfully\n", user);
            }

        } else {
            System.out.println("Channel creation for %#s failed!");
        }
    }
}