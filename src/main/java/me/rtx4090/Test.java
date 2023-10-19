package me.rtx4090;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Discord Bot Token: ");
        String botToken = scanner.nextLine();

        JDA jda = initJDA(botToken);

        if (jda != null) {
            System.out.print("User ID: ");
            String recipientUserId = scanner.nextLine();

            System.out.print("Message: ");
            String messageContent = scanner.nextLine();
            User recipient = jda.getUserById(recipientUserId);

            if (recipient != null) {

                PrivateChannel privateChannel = recipient.openPrivateChannel().complete();

                privateChannel.sendMessage(messageContent).queue();
                System.out.println("Message has been sent to " + recipient.getAsTag() + "successfully");
            } else {
                System.out.println("Can't find the user. Please make sure you've used the correct ID");
            }


            jda.shutdownNow();
        } else {
            System.out.println("JDA launching failed");
        }
    }

    private static JDA initJDA(String token) {
        try {
            return JDABuilder.createDefault(token).build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}