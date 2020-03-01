import eventListeners.KeyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class BotMain {

    public static void main(String[] args) {

        try {
            JDA jda = new JDABuilder("NjgzMTgwMDk2NDgzMzYwNzc4.Xln1QQ.q0UtLieS_OxFSTShAw-KUERdQbk").build();
            jda.addEventListener(new KeyListener());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

}
