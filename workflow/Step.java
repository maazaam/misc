package workflow;

import java.util.ArrayList;
import java.util.List;

public class Step {

    private String name;
    private List<Flow> flows;

    public Step(String name) {
        this.name = name;
        this.flows = new ArrayList<Flow>();
    }

    public String name() {
        return this.name;
    }

    public List<Flow> flows() {
        return this.flows;
    }

    public Flow on(String name) {
        Flow flow = new Flow(name);
        this.flows.add(flow);
        return flow;
    }

    public String process() {
        return null;
    }

    public String toString() {
        return "Step [name=" + this.name + ", flows=" + this.flows + "]";
    }
}
