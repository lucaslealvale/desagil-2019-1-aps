package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nandLeft;
    private final NandGate nandMidTop;
    private final NandGate nandMidBot;
    private final NandGate nandRightTop;
    private final NandGate nandRightBot;


    public HalfAdder() {
        super("Half-Adder", 2, 2);

        nandLeft = new NandGate();

        nandMidTop = new NandGate();
        nandMidTop.connect(1, nandLeft);

        nandMidBot = new NandGate();
        nandMidBot.connect(0, nandLeft);

        nandRightTop = new NandGate();
        nandRightTop.connect(0, nandMidTop);
        nandRightTop.connect(1, nandMidBot);

        nandRightBot = new NandGate();
        nandRightBot.connect(0, nandLeft);
        nandRightBot.connect(1, nandLeft);
    }


    @Override
    public boolean read(int outputPin) {
        if (outputPin != 0) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        return nandRightTop.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandMidTop.connect(0, emitter);
                nandLeft.connect(0, emitter);
                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandMidBot.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}