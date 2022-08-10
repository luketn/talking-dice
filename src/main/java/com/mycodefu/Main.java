package com.mycodefu;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.resume();

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Welcome to talking dice! Press enter to get a new number.\n(type 'quit' and press enter to quit)");
            synthesizer.speakPlainText("Welcome to talking dice!", null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            while (true) {
                String line = console.readLine();
                if (line.equalsIgnoreCase("quit")) {
                    break;
                }
                int dice = new Random().nextInt(1, 7);
                System.out.printf("You rolled a " + dice );
                synthesizer.speakPlainText("" + dice, null);
                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            }

            synthesizer.speakPlainText("Goodbye", null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            synthesizer.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
