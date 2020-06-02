package app.midihost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.usb.UsbDevice;
import android.os.Bundle;

import org.billthefarmer.mididriver.MidiDriver;

import jp.kshoji.driver.midi.device.MidiInputDevice;
import jp.kshoji.driver.midi.device.MidiOutputDevice;
import jp.kshoji.driver.midi.util.UsbMidiDriver;

public class MainActivity extends AppCompatActivity {

    private UsbMidiDriver usbMidiDriver;
    private MidiDriver midiDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usbMidiDriver = new UsbMidiDriver(this) {
            @Override
            public void onDeviceAttached(@NonNull UsbDevice usbDevice) {

            }

            @Override
            public void onMidiInputDeviceAttached(@NonNull MidiInputDevice midiInputDevice) {

            }

            @Override
            public void onMidiOutputDeviceAttached(@NonNull MidiOutputDevice midiOutputDevice) {

            }

            @Override
            public void onDeviceDetached(@NonNull UsbDevice usbDevice) {

            }

            @Override
            public void onMidiInputDeviceDetached(@NonNull MidiInputDevice midiInputDevice) {

            }

            @Override
            public void onMidiOutputDeviceDetached(@NonNull MidiOutputDevice midiOutputDevice) {

            }

            @Override
            public void onMidiMiscellaneousFunctionCodes(@NonNull MidiInputDevice midiInputDevice, int i, int i1, int i2, int i3) {

            }

            @Override
            public void onMidiCableEvents(@NonNull MidiInputDevice midiInputDevice, int i, int i1, int i2, int i3) {

            }

            @Override
            public void onMidiSystemCommonMessage(@NonNull MidiInputDevice midiInputDevice, int i, byte[] bytes) {

            }

            @Override
            public void onMidiSystemExclusive(@NonNull MidiInputDevice midiInputDevice, int i, byte[] bytes) {

            }

            @Override
            public void onMidiNoteOff(@NonNull MidiInputDevice midiInputDevice, int i, int i1, int i2, int i3) {
                //Log.i("MidiNoteOff", String.format(Locale.US, "%d %d %d %d", i, i1, i2, i3));
                byte[] event = new byte[3];
                event[0] = (byte) 0x80;
                event[1] = (byte) i2;
                midiDriver.write(event);
            }

            @Override
            public void onMidiNoteOn(@NonNull MidiInputDevice midiInputDevice, int i, int i1, int i2, int i3) {
                //Log.i("MidiNoteOn", String.format(Locale.US, "%d %d %d %d", i, i1, i2, i3));
                byte[] event = new byte[3];
                event[0] = (byte) 0x90;
                event[1] = (byte) i2;
                event[2] = (byte) 0x7f; //i3;
                midiDriver.write(event);
            }

            @Override
            public void onMidiPolyphonicAftertouch(@NonNull MidiInputDevice midiInputDevice, int i, int i1, int i2, int i3) {

            }

            @Override
            public void onMidiControlChange(@NonNull MidiInputDevice midiInputDevice, int i, int i1, int i2, int i3) {

            }

            @Override
            public void onMidiProgramChange(@NonNull MidiInputDevice midiInputDevice, int i, int i1, int i2) {

            }

            @Override
            public void onMidiChannelAftertouch(@NonNull MidiInputDevice midiInputDevice, int i, int i1, int i2) {

            }

            @Override
            public void onMidiPitchWheel(@NonNull MidiInputDevice midiInputDevice, int i, int i1, int i2) {

            }

            @Override
            public void onMidiSingleByte(@NonNull MidiInputDevice midiInputDevice, int i, int i1) {

            }

            @Override
            public void onMidiTimeCodeQuarterFrame(@NonNull MidiInputDevice midiInputDevice, int i, int i1) {

            }

            @Override
            public void onMidiSongSelect(@NonNull MidiInputDevice midiInputDevice, int i, int i1) {

            }

            @Override
            public void onMidiSongPositionPointer(@NonNull MidiInputDevice midiInputDevice, int i, int i1) {

            }

            @Override
            public void onMidiTuneRequest(@NonNull MidiInputDevice midiInputDevice, int i) {

            }

            @Override
            public void onMidiTimingClock(@NonNull MidiInputDevice midiInputDevice, int i) {

            }

            @Override
            public void onMidiStart(@NonNull MidiInputDevice midiInputDevice, int i) {

            }

            @Override
            public void onMidiContinue(@NonNull MidiInputDevice midiInputDevice, int i) {

            }

            @Override
            public void onMidiStop(@NonNull MidiInputDevice midiInputDevice, int i) {

            }

            @Override
            public void onMidiActiveSensing(@NonNull MidiInputDevice midiInputDevice, int i) {

            }

            @Override
            public void onMidiReset(@NonNull MidiInputDevice midiInputDevice, int i) {

            }
        };
        usbMidiDriver.open();
        midiDriver = new MidiDriver();
    }

    @Override
    protected void onDestroy() {
        usbMidiDriver.close();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        midiDriver.start();
        midiDriver.setVolume(75);
    }

    @Override
    protected void onPause() {
        midiDriver.stop();
        super.onPause();
    }
}
