/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package ca.bazlur;

import org.openjdk.jmh.annotations.Benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 20, time = 1)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@OperationsPerInvocation
public class MapBenchmark {
    private static final int SIZE = 10;

    private Map<Integer, String> mapOf;
    private Map<Integer, String> hashMap;

    @Setup
    public void setup() {
        mapOf = Map.of(
                0, "value0",
                1, "value1",
                2, "value2",
                3, "value3",
                4, "value4",
                5, "value5",
                6, "value6",
                7, "value7",
                8, "value8",
                9, "value9"
        );

        hashMap = new HashMap<>();

        hashMap.put(0, "value0");
        hashMap.put(1, "value1");
        hashMap.put(2, "value2");
        hashMap.put(3, "value3");
        hashMap.put(4, "value4");
        hashMap.put(5, "value5");
        hashMap.put(6, "value6");
        hashMap.put(7, "value7");
        hashMap.put(8, "value8");
        hashMap.put(9, "value9");
    }

    @Benchmark
    public void testMapOf(Blackhole blackhole) {
        Map<Integer, String> map = Map.of(
                0, "value0",
                1, "value1",
                2, "value2",
                3, "value3",
                4, "value4",
                5, "value5",
                6, "value6",
                7, "value7",
                8, "value8",
                9, "value9"
        );
        blackhole.consume(map);
    }


    @Benchmark
    public void testHashMap(Blackhole blackhole) {
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(0, "value0");
        hashMap.put(1, "value1");
        hashMap.put(2, "value2");
        hashMap.put(3, "value3");
        hashMap.put(4, "value4");
        hashMap.put(5, "value5");
        hashMap.put(6, "value6");
        hashMap.put(7, "value7");
        hashMap.put(8, "value8");
        hashMap.put(9, "value9");
        blackhole.consume(hashMap);
    }

    @Benchmark
    public void testGetMapOf() {
        for (int i = 0; i < 10; i++) {
            mapOf.get(i);
        }
    }

    @Benchmark
    public void testGetHashMap() {
        for (int i = 0; i < SIZE; i++) {
            hashMap.get(i);
        }
    }
}
