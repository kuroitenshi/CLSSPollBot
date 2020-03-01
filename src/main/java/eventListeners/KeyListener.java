package eventListeners;

import model.TaskHandler;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import utility.BotConstants;

import java.util.Objects;

public class KeyListener extends ListenerAdapter {

    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        String[] messageSent = event.getMessage().getContentRaw().split(" ");
        String botResponse = "";

        switch (messageSent[0]) {

            case BotConstants.addOption: {
                if (!Objects.requireNonNull(event.getMember()).getUser().isBot()) {
                    botResponse = TaskHandler.getInstance().addTask(messageBuilder(messageSent));

                }
            }
            break;
            case BotConstants.clearPoll: {
                botResponse = TaskHandler.getInstance().clearTaskHandler();

            }
            break;
            case BotConstants.upvote: {
                botResponse = TaskHandler.getInstance().addVote("<@" + event.getAuthor().getId() +">", messageBuilder(messageSent));
            }
            break;
            case BotConstants.downvote: {
                botResponse = TaskHandler.getInstance().removeVote("<@" + event.getAuthor().getId() +">");
            }
            default: {

            }
        }
        event.getChannel().sendMessage(botResponse).queue();

    }

    private String messageBuilder(String[] messages) {
        StringBuilder option = new StringBuilder();
        for (int i = 1; i < messages.length; i++) {
            option.append(messages[i]);
        }

        return option.toString();

    }
}
