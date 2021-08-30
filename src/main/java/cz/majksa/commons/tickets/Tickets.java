/*
 *  tickets - cz.majksa.commons.tickets.TicketsManager
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

package cz.majksa.commons.tickets;

import cz.majksa.commons.tickets.ticketer.Ticketer;
import lombok.Data;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p><b>Class {@link Tickets}</b></p>
 *
 * @author majksa
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class Tickets {

    private final Map<Member, TicketOwner> users = new ConcurrentHashMap<>();
    private final Ticketer ticketer;

    /**
     * The new ticket name format
     * <br><code>{name}</code> will be replaced with <code>ticket name</code>
     * <br><code>{member}</code> will be replaced with {@link net.dv8tion.jda.api.entities.Member#getEffectiveName()}
     * <br><code>{user}</code> will be replaced with {@link net.dv8tion.jda.api.entities.User#getAsTag()}
     * <br><code>{id}</code> will be replaced with {@link net.dv8tion.jda.api.entities.Member#getId()}
     */
    private String format = "{member}-{name}";

    public CompletableFuture<TextChannel> open(@Nonnull Member member, @Nonnull String name) {
        final String ticketName = getTicketName(member, name);
        return ticketer.open(ticketName).thenApply(channel -> {
            getMemberTickets(member).addTicket(channel);
            return channel;
        });
    }

    public CompletableFuture<Void> close(@Nonnull Member member) {
        return CompletableFuture.allOf(
                getMemberTickets(member)
                        .getTickets()
                        .stream()
                        .map(ticketer::close)
                        .toArray(CompletableFuture[]::new)
        );
    }

    public CompletableFuture<Void> closeAll() {
        return CompletableFuture.allOf(
                users.keySet()
                        .stream()
                        .map(this::close)
                        .toArray(CompletableFuture[]::new)
        );
    }

    public TicketOwner getMemberTickets(@Nonnull Member member) {
        users.computeIfAbsent(member, TicketOwner::new);
        return users.get(member);
    }

    private String getTicketName(@Nonnull Member member, @Nonnull String name) {
        return format
                .replaceAll("\\{name}", name)
                .replaceAll("\\{member}", member.getEffectiveName())
                .replaceAll("\\{user}", member.getUser().getAsTag())
                .replaceAll("\\{id}", member.getId());
    }

}
