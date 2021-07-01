import java.util.*;
import java.util.stream.Collectors;

public class SistemaTickets {
    private Map< String , Tecnico > funcionarios ; // funcionarios da empresa
    private List< Ticket > ticketsPorResolver ; // tickets ainda n~ao tratados
    private List < Ticket > ticketsResolvidos ; // tickets j´a satisfeitos



    public void adicionaTicket(Ticket t){
        funcionarios.k
        ticketsPorResolver.add(t.clonem());
    }

    public void resolveTicket(String ident) throws TecnicoNaoExisteException{

        Map<String,List<Ticket>> m = ticketsPorResolver.stream().collect(Collectors.groupingBy(Ticket :: getIdentTecnico));

        if (funcionarios.containsKey(ident)){
            Ticket temp = ticketsPorResolver.remove(0);
            temp.setIdentTecnico(ident);temp.executaTarefa();
            ticketsResolvidos.add(temp);
        }
        else new TecnicoNaoExisteException();
    }

    public Map<String,List<Ticket>> ticketsPorTecnico(){
        Map<String, List<Ticket>> tpT = new HashMap<>();
        funcionarios.values().forEach(f -> tpT.put(f.getNome(),
                ticketsResolvidos.stream().filter(ticket -> ticket.getIdentTecnico().equals(f.getNome())).collect(Collectors.toList())));
        return tpT;

    }

    public String supervisorTop(){
        Map<String,List<Ticket>> n = this.ticketsPorTecnico();
        return n.values().stream().sorted().findFirst().get().get(0).getIdentTecnico();
    }

}

