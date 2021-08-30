/*
 *  tickets - cz.majksa.commons.tickets.ticketer.ThreadTicketer
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

package cz.majksa.commons.tickets.ticketer;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.TextChannel;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * <p><b>Class {@link cz.majksa.commons.tickets.ticketer.ThreadTicketer}</b></p>
 *
 * @author majksa
 * @version 1.0.0
 * @since 1.0.0
 * @deprecated Not supported by JDA yet
 */
@Deprecated
@RequiredArgsConstructor
public class ThreadTicketer implements Ticketer {

    @Nonnull
    private final TextChannel mainChannel;

    @Override
    public CompletableFuture<TextChannel> open(@NotNull String name) {
        throw new IllegalStateException("Not supported by JDA");
    }

    @NotNull
    @Override
    public CompletableFuture<Void> close(@NotNull TextChannel channel) {
        throw new IllegalStateException("Not supported by JDA");
    }

}
