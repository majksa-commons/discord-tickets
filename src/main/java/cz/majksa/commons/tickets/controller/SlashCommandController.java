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
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * <p><b>Class {@link cz.majksa.commons.tickets.controller.SlashCommandController}</b></p>
 *
 * @author majksa
 * @version 1.0.0
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class SlashCommandController extends ListenerAdapter implements Controller {

    @Nonnull
    private final Tickets manager;
    @Nonnull
    private final WrappedCommand command;

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        if (event.getCommandId().equals(command.jda.getId())) {
            event.deferReply(true).submit();
            manager.open(Objects.requireNonNull(event.getMember()), command.getName()).join();
            event.getHook().editOriginal("Successfully opened a new ticket for you!").submit();
        }
    }

    @Override
    public void start() {
        command.jda.getJDA().addEventListener(this);
    }

    @Override
    public void stop() {
        command.jda.getJDA().removeEventListener(this);
    }

    @RequiredArgsConstructor
    public static class WrappedCommand {

        private final Command jda;

        public String getName() {
            return "";
        }

    }


}
