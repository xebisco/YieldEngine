/*
 * Copyright [2022-2024] [Xebisco]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xebisco.yield.physics;

import com.xebisco.yield.ImmutableVector2D;
import com.xebisco.yield.Scene;
import com.xebisco.yield.Vector2D;
import org.jbox2d.common.Vec2;

public final class Utils {
    public static Vec2 toVec2(Vector2D v) {
        return new Vec2((float) v.x(), (float) v.y());
    }

    public static Vector2D toVector2D(Vec2 v) {
        return new Vector2D(v.x, v.y);
    }
    public static ImmutableVector2D toImmutableVector2D(Vec2 v) {
        return new ImmutableVector2D(v.x, v.y);
    }
}
