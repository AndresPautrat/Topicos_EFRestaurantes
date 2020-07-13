import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.wrapper.AgentController;
import jade.wrapper.PlatformController;

public class HostAgent extends Agent {
    int Npersonas = 25;
    int Mrestaurantes = 5;

    @Override
    protected void setup() {
        // TODO Auto-generated method stub
        try {
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());
            DFService.register(this, dfd);
            addBehaviour(new OneShotBehaviour() {

                @Override
                public void action() {
                    int Cis[] = { 5, 10, 25, 15, 10 };
                    // TODO Auto-generated method stub
                    PlatformController container = getContainerController();
                    for (int i = 0; i < Npersonas; i++) {
                        try {
                            String localName = "Persona" + i;
                            AgentController ac = container.createNewAgent(localName, "PersonaAgent", null);
                            ac.start();
                        } catch (Exception e) {
                            System.err.println("exceptiion " + e);
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < Mrestaurantes; i++) {
                        try {
                            String localName = "Restaurante" + i;
                            AgentController ac = container.createNewAgent(localName, "RestauranteAgent", null);
                            ac.start();
                        } catch (Exception e) {
                            System.err.println("exceptiion " + e);
                            e.printStackTrace();
                        }
                    }
                    /*
                     * for (int i : Cis) { ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                     * msg.setContent("volando"); msg.addReceiver(new AID("Torre",
                     * AID.ISLOCALNAME)); send(msg); }
                     */

                }

            });
        } catch (Exception e) {
            System.err.println("exceptiion " + e);
            e.printStackTrace();
        }
    }

    void crearAgentes(PlatformController container) {
        try {
            String localName = "Torre";
            AgentController ac = container.createNewAgent(localName, "AvionAgent", null);
            ac.start();
        } catch (Exception e) {
            System.err.println("exceptiion " + e);
            e.printStackTrace();
        }
    }
}