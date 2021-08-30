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
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;


/**
 * <p><b>Class {@link cz.majksa.commons.tickets.controller.EmojiController}</b></p>
 *
 * @author majksa
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EmojiController extends ListenerAdapter implements Controller {

    @Nonnull
    private final Tickets manager;
    @Nonnull
    private Message message;
    @Nonnull
    private MessageReaction.ReactionEmote button;

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        if (!message.getId().equals(event.getMessageId())) {
            return;
        }
        if (button.equals(event.getReactionEmote())) {
            manager.open(Objects.requireNonNull(event.getMember()), "");
        }
    }

    @Override
    public void start() {
        message.getJDA().addEventListener(this);
    }

    @Override
    public void stop() {
        message.getJDA().removeEventListener(this);
    }

}
