/*
 *  tickets - cz.majksa.commons.tickets.ticketer.CategoryTicketer
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
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.TextChannel;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * <p><b>Class {@link cz.majksa.commons.tickets.ticketer.CategoryTicketer}</b></p>
 *
 * @author majksa
 * @version 1.0.0
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class CategoryTicketer implements Ticketer {

    @Nonnull
    private final Category category;

    @Override
    public CompletableFuture<TextChannel> open(@Nonnull String name) {
        return category.createTextChannel(name).submit();
    }

    @NotNull
    @Override
    public CompletableFuture<Void> close(@NotNull TextChannel channel) {
        return channel.delete().submit();
    }

}
