package com.example.synthesizer;

public class VolumeAdjuster implements AudioComponent{
    AudioComponent input;
    double scale = 1; // Set default to 1 so the input doesn't change by default

    VolumeAdjuster(){
        input = null;
    }

    VolumeAdjuster(AudioComponent input){
        this.input = input;
    }

    VolumeAdjuster(AudioComponent input, double scale){
        this.input = input;
        this.scale = scale;
    }

    void setScale(double scale){this.scale = scale;}

    @Override
    public AudioClip getClip() {
        AudioClip inputclip = input.getClip();
        AudioClip clip = new AudioClip();
        for (int i = 0; i < AudioClip.sampleRate; i++){
            clip.setSample(i, (short)(inputclip.getSample(i) * scale));
        }
        return clip;
    }

    @Override
    public boolean hasInput() {
        return input != null;
    }

    @Override
    public void connectInput(AudioComponent component) {
        input = component;
    }
}
