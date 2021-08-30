/*
 *  tickets - cz.majksa.commons.tickets.listener.TicketListener
 *  Copyright (C) 2021  Majksa
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cz.majksa.commons.tickets.controller;

import cz.majksa.commons.tickets.Tickets;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.Button;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * <p><b>Class {@link ButtonController}</b></p>
 *
 * @author majksa
 * @version 1.0.0
 * @since 1.0.0
 */
@AllArgsConstructor
public class ButtonController extends ListenerAdapter implements Controller {

    @Nonnull
    private final Tickets manager;
    @Nonnull
    private Message botMessage;
    @Nonnull
    private Button button;

    public ButtonController(@Nonnull Tickets manager, @Nonnull TextChannel channel, @Nonnull MessageBuilder builder, @Nonnull Button button) {
        this.manager = manager;
        this.button = button;
        setBotMessage(channel, builder, button);
    }

    public void setBotMessage(@Nonnull MessageBuilder builder, @Nonnull Button button) {
        setBotMessage(botMessage.getTextChannel(), builder, button);
    }

    public void setBotMessage(@Nonnull TextChannel channel, @Nonnull MessageBuilder builder, @Nonnull Button button) {
        channel.sendMessage(builder.build())
                .setActionRow(button)
                .submit()
                .thenAccept(message -> botMessage = message)
                .join();
    }

    @Override
    public void onButtonClick(@NotNull ButtonClickEvent event) {
        if (event.getComponentId().equals(button.getId())) {
            final InteractionHook hook = event.deferReply(true).submit().join();
            manager.open(Objects.requireNonNull(event.getMember()), "").join();
            hook.editOriginal("Successfully opened a new ticket for you!").submit().join();
        }
    }

    @Override
    public void start() {
        botMessage.getJDA().addEventListener(this);
    }

    @Override
    public void stop() {
        botMessage.getJDA().removeEventListener(this);
    }

}
