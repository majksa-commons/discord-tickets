/*
 *  tickets - cz.majksa.commons.tickets.TicketOwner
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

import lombok.Data;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

/**
 * <p><b>Class {@link cz.majksa.commons.tickets.TicketOwner}</b></p>
 *
 * @author majksa
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class TicketOwner {

    @Nonnull
    private final Set<TextChannel> tickets = new HashSet<>();
    @Nonnull
    private final Member member;

    public boolean addTicket(@Nonnull TextChannel ticket) {
        tickets.add(ticket);
        return tickets.size() == 1;
    }

    public boolean closeTicket(@Nonnull TextChannel ticket) {
        tickets.remove(ticket);
        return tickets.size() == 0;
    }

}
