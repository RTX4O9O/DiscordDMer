package me.rtx4090;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.User;

public class Main extends ListenerAdapter {
    private JDA jda;

    public Main() {
        String botToken = "MTE1Nzg5NjQwNDczNjQyMTg5OQ.Gq4mVV.pU4M0FId1BTwAn-BPi5j8e81d5zfaR1AwR7fWA";
        jda = JDABuilder.createDefault(botToken).addEventListeners(this).build();
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        System.out.println("All members in the guild " + event.getGuild().getName() + " have been downloaded.");

        // Now you can be sure that all server members are loaded
        sendPrivateMessage();
    }

    private void sendPrivateMessage() {
        String recipientUserId = "860877200823025664"; // Replace with the actual user ID
        String messageContent = "L";

        User recipient = jda.getUserById(recipientUserId);
        System.out.println(recipient);

        if (recipient != null) {
            recipient.openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage(messageContent).queue();
                System.out.println("Message has been sent to " + recipient.getAsTag() + " successfully");
            });
        } else {
            System.out.println("Can't find the user. Please make sure you've used the correct ID");
        }
    }
}