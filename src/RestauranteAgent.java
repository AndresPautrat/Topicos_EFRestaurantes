import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class RestauranteAgent extends Agent {
    int CiCapacidad = 0;
    int reservados = 0;

    @Override
    protected void setup() {
        try {
            ServiceDescription sd = new ServiceDescription();
            sd.setType("TorrentAgent");
            sd.setName("TorrentAgentDescription");
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());

            DFService.register(this, dfd);
        } catch (FIPAException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        addBehaviour(new OneShotBehaviour() {

            @Override
            public void action() {
                // TODO Auto-generated method stub
                switch (getLocalName()) {
                    case "Restaurante0":
                        CiCapacidad = 5;
                        break;
                    case "Restaurante1":
                        CiCapacidad = 10;
                        break;
                    case "Restaurante2":
                        CiCapacidad = 25;
                        break;
                    case "Restaurante3":
                        CiCapacidad = 15;
                        break;
                    case "Restaurante4":
                        CiCapacidad = 10;
                }
            }

        });
        addBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {
                ACLMessage msg = myAgent.receive();
                if (msg != null) {
                    tratarMensajes(msg);
                } else
                    block();
            }
        });
    }

    void tratarMensajes(ACLMessage msg) {
        ACLMessage reply = msg.createReply();
        switch (msg.getContent()) {
            case "buscando":
                if (reservados < CiCapacidad) {
                    System.out.println(getLocalName() + ":mensaje de " + msg.getSender().getLocalName());
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("si");
                    reservados++;
                    break;
                } else {
                    System.out.println(getLocalName() + ":mensaje de " + msg.getSender().getLocalName());
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("no");
                    break;
                }

        }
    }
}