package me.rtx4090;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.Scanner;

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
        String quitStr;
        sendPrivateMessage();
    }

    private void sendPrivateMessage() {
        boolean sent = false;
        ArrayList<String> messageContent = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("User ID: ");
        String recipientUserId = scanner.nextLine();
        System.out.print("Message: ");
        messageContent.add(scanner.nextLine());

        jda.openPrivateChannelById(recipientUserId).queue(channel -> {
            User user = channel.getUser();
            channel.sendMessage(messageContent.get(messageContent.size()-1)).queue();
            System.out.printf("Message has been sent to %#s successfully\n", user);
            //sent = true;


        });
        while (true) {
            while (sent){

                System.out.print("User ID: ");
                recipientUserId = scanner.nextLine();

                System.out.print("Message: ");
                messageContent = scanner.nextLine();

                String finalMessageContent = messageContent;
                jda.openPrivateChannelById(recipientUserId).queue(channel -> {
                    User user = channel.getUser();
                    channel.sendMessage(finalMessageContent).queue();
                    System.out.printf("Message has been sent to %#s successfully\n", user);
                    //sent = true;
                });

            }
        }


    }
}