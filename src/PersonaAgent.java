import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class PersonaAgent extends Agent {
    String restaurantes[] = { "Restaurante0", "Restaurante1", "Restaurante2", "Restaurante3", "Restaurante4" };
    String otrasPersonas[] = { "Persona1", "Persona0", "Persona3", "Persona2", "Persona4", "Persona5", "Persona6",
            "Persona7", "Persona8", "Persona9", "Persona10", "Persona11", "Persona12", "Persona13", "Persona14",
            "Persona15", "Persona16", "Persona17", "Persona18", "Persona20", "Persona19", "Persona22", "Persona23",
            "Persona24", "Persona21" };

    @Override
    protected void setup() {
        try {
            ServiceDescription sd = new ServiceDescription();
            sd.setType("PersonaAgent");
            sd.setName("PersonaAgentDescription");
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(getLocalName());

        ParallelBehaviour parallel = new ParallelBehaviour();
        parallel.addSubBehaviour(new TickerBehaviour(this, 5000) {

            @Override
            protected void onTick() {
                reserva();
            }

        });
        parallel.addSubBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {
                // TODO Auto-generated method stub
                ACLMessage msg = myAgent.receive();
                if (msg != null) {
                    switch (msg.getContent()) {
                        case "si":
                            System.out.println("econtre");
                    }
                }
            }

        });
        addBehaviour(parallel);
    }

    void mesages(ACLMessage msg) {
        ACLMessage reply = msg.createReply();
    }

    void reserva() {
        for (String string : restaurantes) {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContent("buscando");
            msg.addReceiver(new AID(string, AID.ISLOCALNAME));
            send(msg);
        }
    }

    void deliberar() {
    }
}