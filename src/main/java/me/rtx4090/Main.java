package me.rtx4090;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.User;
// import java.util.ArrayList;
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
        sendPrivateMessage();
    }

    private void sendPrivateMessage() {
        boolean sent = false;
        final ArrayList<String> messageContent = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            loop(scanner);
        }

//        do{
//            sent = false;
//
//            System.out.print("User ID: ");
//            String recipientUserId = scanner.nextLine();
//
//            System.out.print("Message: ");
//            messageContent.add(scanner.nextLine());
//
//            PrivateChannel channel = jda.openPrivateChannelById(recipientUserId).completeAfter(1, TimeUnit.SECONDS);
//            if (channel!= null) {
//                User user = channel.getUser();
//                channel.sendMessage(messageContent.get(messageContent.size() - 1)).queue();
//                System.out.printf("Message has been sent to %#s successfully\n", user);
//                sent = true;
//            } else {
//                System.out.println("Channel creation for %#s failed!");
//            }
//        } while (sent=true);

        // 🤓🤓 how are u gonna exit ctrl+c?
    }


    }
}