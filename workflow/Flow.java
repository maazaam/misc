package workflow;

import java.util.ArrayList;
import java.util.List;

public class Flow {

    private String name;
    private List<Step> steps;

    public Flow(String name) {
        this.name = name;
        this.steps = new ArrayList<Step>();
    }

    public String name() {
        return this.name;
    }

    public List<Step> steps() {
        return this.steps;
    }

    public Flow add(Step step) {
        this.steps.add(step);
        return this;
    }

    public String process() {
        process(this);
        return null;
    }

    private void process(Flow flow) {
        System.out.println("executing flow: " + flow.name());
        for (Step step : flow.steps()) {
            process(step);
        }
    }

    private void process(Step step) {
        System.out.println("executing step: " + step.name());
        String name = step.process();
        for (Flow flow : step.flows()) {
            if (flow.name().equals(name)) {
                process(flow);
            }
        }
    }

    public String toString() {
        return "Flow [name=" + this.name + ", steps=" + this.steps + "]";
    }
}
