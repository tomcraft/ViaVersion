/*
 * This file is part of ViaVersion - https://github.com/ViaVersion/ViaVersion
 * Copyright (C) 2016-2021 ViaVersion and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.viaversion.viaversion.api.type.types.version;


import io.netty.buffer.ByteBuf;
import com.viaversion.viaversion.api.minecraft.metadata.Metadata;
import com.viaversion.viaversion.api.minecraft.metadata.types.MetaType1_8;
import com.viaversion.viaversion.api.type.types.minecraft.MetaTypeTemplate;

public class Metadata1_8Type extends MetaTypeTemplate {

    @Override
    public Metadata read(ByteBuf buffer) throws Exception {
        byte item = buffer.readByte();
        if (item == 127) return null; // end of metadata
        int typeID = (item & 0xE0) >> 5;
        MetaType1_8 type = MetaType1_8.byId(typeID);
        int id = item & 0x1F;
        return new Metadata(id, type, type.getType().read(buffer));
    }

    @Override
    public void write(ByteBuf buffer, Metadata meta) throws Exception {
        byte item = (byte) (meta.getMetaType().getTypeID() << 5 | meta.getId() & 0x1F);
        buffer.writeByte(item);
        meta.getMetaType().getType().write(buffer, meta.getValue());
    }
}
