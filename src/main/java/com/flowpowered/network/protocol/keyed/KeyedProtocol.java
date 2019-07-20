/*
 * This file is part of Flow Network, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013 Flow Powered <https://flowpowered.com/>
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
package com.flowpowered.network.protocol.keyed;

import com.flowpowered.network.Codec;
import com.flowpowered.network.Codec.CodecRegistration;
import com.flowpowered.network.Message;
import com.flowpowered.network.MessageHandler;
import com.flowpowered.network.protocol.AbstractProtocol;
import com.flowpowered.network.service.CodecLookupService;
import com.flowpowered.network.service.HandlerLookupService;
import org.slf4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A {@code AbstractProtocol} stores {@link Message}s and their respective {@link Codec}s and {@link MessageHandler}s by key.
 */
public abstract class KeyedProtocol extends AbstractProtocol {
    private final int maxPackets;
    private final ConcurrentMap<String, CodecLookupService> codecLookup;
    private final ConcurrentMap<String, HandlerLookupService> handlerLookup;

    /**
     * @param name The name of this protocol
     * @param maxPackets See {@link CodecLookupService}
     */
    public KeyedProtocol(String name, int maxPackets) {
        super(name);
        this.maxPackets = maxPackets;
        codecLookup = new ConcurrentHashMap<>();
        handlerLookup = new ConcurrentHashMap<>();
    }

    /**
     * @param name The name of this protocol
     * @param maxPackets See {@link CodecLookupService}
     * @param logger The logger to use for output
     */
    public KeyedProtocol(String name, int maxPackets, Logger logger) {
        super(name, logger);
        this.maxPackets = maxPackets;
        codecLookup = new ConcurrentHashMap<>();
        handlerLookup = new ConcurrentHashMap<>();
    }

    /**
     * Gets the handler lookup service associated with this AbstractProtocol
     *
     * @param key The string key that a protocol was registered with.
     *
     * @return the handler lookup service, or null if none
     */
    protected HandlerLookupService getHandlerLookupService(String key) {
        return handlerLookup.get(key);
    }

    /**
     * Gets the codec lookup service associated with this AbstractProtocol
     *
     * @param key The string key that a protocol was registered with.
     *
     * @return the codec lookup service, or null if none
     */
    protected CodecLookupService getCodecLookupService(String key) {
        return codecLookup.get(key);
    }

    public <M extends Message, C extends Codec<? super M>, H extends MessageHandler<?, ? super M>> CodecRegistration registerMessage(String key, Class<M> message, Class<C> codec, Class<H> handler, Integer opcode) {
        try {
            CodecLookupService codecLookup = this.codecLookup
                .computeIfAbsent(key, k -> new CodecLookupService(maxPackets));
            HandlerLookupService handlerLookup = this.handlerLookup
                .computeIfAbsent(key, k -> new HandlerLookupService());
            CodecRegistration bind = codecLookup.bind(message, codec, opcode);
            if (bind != null && handler != null) {
                handlerLookup.bind(message, handler);
            }
            return bind;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            getLogger().error(MessageFormat.format("Error registering codec {0}: ", codec), e);
            return null;
        }
    }

}