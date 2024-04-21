/*
 * This file is part of Flow Network, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013-2022 MiniMiners.de <https://miniminers.de/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.flowpowered.network;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * {@code Codec}s are used to encode and decode a {@link Message} into a {@link ByteBuf}.
 */
public interface Codec<T extends Message> {
    /**
     * Decodes a {@link ByteBuf} into a {@link Message}.
     *
     * @param buffer the buffer to read from
     * @return the message fully encoded.
     * @throws IOException If any decoding fails on the buffer
     */
    T decode(CodecContext context, ByteBuf buffer) throws IOException;

    /**
     * Encodes a {@link Message} into a {@link ByteBuffer}.
     *
     * @param buf the buffer to encode into. Should be empty.
     * @param message The message to encode
     * @return the ByteBuf encoded into
     * @throws IOException If any data on the message fails to encode
     */
    ByteBuf encode(CodecContext context, ByteBuf buf, T message) throws IOException;

    class CodecRegistration {
        private final int opcode;
        private final Codec<?> codec;

        public CodecRegistration(int opcode, Codec<?> codec) {
            this.opcode = opcode;
            this.codec = codec;
        }

        public int getOpcode() {
            return opcode;
        }

        public <M extends Message> Codec<M> getCodec() {
            return (Codec<M>) codec;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 67 * hash + this.opcode;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final CodecRegistration other = (CodecRegistration) obj;
            return this.opcode == other.opcode;
        }
    }
}
