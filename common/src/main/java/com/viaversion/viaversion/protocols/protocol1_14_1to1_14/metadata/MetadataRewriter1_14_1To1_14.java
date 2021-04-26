/*
 * This file is part of ViaVersion - https://github.com/ViaVersion/ViaVersion
 * Copyright (C) 2016-2021 ViaVersion and contributors
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.viaversion.viaversion.protocols.protocol1_14_1to1_14.metadata;

import com.viaversion.viaversion.api.data.UserConnection;
import com.viaversion.viaversion.api.entities.Entity1_14Types;
import com.viaversion.viaversion.api.entities.EntityType;
import com.viaversion.viaversion.api.minecraft.metadata.Metadata;
import com.viaversion.viaversion.api.rewriters.MetadataRewriter;
import com.viaversion.viaversion.protocols.protocol1_14_1to1_14.Protocol1_14_1To1_14;
import com.viaversion.viaversion.protocols.protocol1_14_1to1_14.storage.EntityTracker1_14_1;

import java.util.List;

public class MetadataRewriter1_14_1To1_14 extends MetadataRewriter {

    public MetadataRewriter1_14_1To1_14(Protocol1_14_1To1_14 protocol) {
        super(protocol, EntityTracker1_14_1.class);
    }

    @Override
    public void handleMetadata(int entityId, EntityType type, Metadata metadata, List<Metadata> metadatas, UserConnection connection) {
        if (type == null) return;

        if (type == Entity1_14Types.VILLAGER || type == Entity1_14Types.WANDERING_TRADER) {
            if (metadata.getId() >= 15) {
                metadata.setId(metadata.getId() + 1);
            }
        }
    }

    @Override
    protected EntityType getTypeFromId(int type) {
        return Entity1_14Types.getTypeFromId(type);
    }
}
