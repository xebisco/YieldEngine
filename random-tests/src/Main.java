/*
 * Copyright [2022-2023] [Xebisco]
 *
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

import com.xebisco.yield.*;
import com.xebisco.yield.physics.*;

public class Main extends Scene {
    public Main(Application application) {
        super(application);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.setProperty("sun.java2d.opengl", "True");
        ContextTime time = new ContextTime();
        ApplicationManager applicationManager = new ApplicationManager(time);
        PlatformInit platformInit = new PlatformInit();
        Application application = new Application(applicationManager, Main.class, Global.swingALPlatform(), platformInit);
        applicationManager.getApplications().add(application);
        applicationManager.run();
    }

    private Vector2D pos = new Vector2D();

    @Override
    public void onStart() {
        instantiate(StandardPrefabs.texRectangle("com/xebisco/yield/img.png")).getComponent(TextureRectangle.class).getVertexShaders().add(new VertexShader() {
            @Override
            public void run() {
                if (index == 3)
                    position.sumLocal(pos);
            }
        });
    }

    @Override
    public void onUpdate() {
        pos.sumLocal(getApplication().getAxis(HORIZONTAL, VERTICAL));
    }
}