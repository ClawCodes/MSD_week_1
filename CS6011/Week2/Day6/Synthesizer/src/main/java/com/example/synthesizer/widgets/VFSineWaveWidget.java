package com.example.synthesizer.widgets;

import com.example.synthesizer.VFSineWave;
import javafx.scene.layout.AnchorPane;

public class VFSineWaveWidget extends AudioComponentWidget {
    public VFSineWaveWidget(AnchorPane pane, String name) {
        super(new VFSineWave(), pane, name);
        setSlider(1000, 10000, "setMaxValue", Short.class);
    }
}
