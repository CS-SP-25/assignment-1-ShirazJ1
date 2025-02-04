public class SalesTaxCalculator {

    public static void main(String[] args) throws Exception {
        // storing command line arguments in variables.
        String stateName = args[0];
        Double sale = Double.parseDouble(args[1]);
        // conditions to check which state is given
        if (stateName.equalsIgnoreCase("Alaska")) {
            // initializing the state object depending on the user input.
            State alaska = new Alaska(stateName);
            // setting the behavior dynamically once the state is identified
            alaska.setBehavior(new NoTax());
            // prints the output.
            alaska.showTax(sale);
        }
        else if (stateName.equalsIgnoreCase("Indiana")) {
            // initialzing the Indiana state object to match user's input.
            State indiana = new Indiana(stateName);
            // setting the behavior dynamically once the state is identified
            indiana.setBehavior(new SevenPercent());
            // prints the outpu
            indiana.showTax(sale);
        }
        else if (stateName.equalsIgnoreCase("Hawaii")) {
            // initialzing the Hawaii state object to match user's input.
            Hawaii hawaii = new Hawaii(stateName);
            // setting the behavior dynamically once the state is identified
            hawaii.setBehavior(new FourPointFivePercent());
            // prints the output
            hawaii.showTax(sale);
        }
        // throws an exception if a state other than IN or AL or HW is given
        else {
            throw new Exception("Invalid State");
        }
    }
}

interface SalesTaxBehavior {
    // compute function prototype 
    public double compute(double value);
}

// NoTax class dependent on salestaxbehavior interface.
class NoTax implements SalesTaxBehavior {
    // overriding compute function
    // to match the NoTax implementation 
    @Override
    public double compute(double value) {
        return 0.00;
    }
}

class SevenPercent implements SalesTaxBehavior {
    // overriding compute function
    // to match SevenPercent implementation
    @Override
    public double compute(double value) {
        return (value * 0.07);
    }

}

// New 4.5% sale tax
class FourPointFivePercent implements SalesTaxBehavior {
    @Override
    public double compute(double value) {
        return (value * 0.045);
    }
}

// abstract class to extend Alaska and Indiana.
abstract class State {
    // private variable
    private String name;
    private SalesTaxBehavior taxBehavior;
    // getter
    public String getName() {
        return this.name;
    }

    public SalesTaxBehavior getBehavior() {
        return this.taxBehavior;
    }
    //setter
    public void setName(String newName) {
        this.name = newName;
    }
    // base showTax method that gets overriden by derived classes.
    public void showTax(Double value) {
        System.out.println("State Tax");
    }

    public void setBehavior(SalesTaxBehavior behavior) {
        this.taxBehavior = behavior;
    }

    public void setNewBehavior(SalesTaxBehavior behavior) {
        this.taxBehavior = behavior; 
    }

}

class Alaska extends State {
    
    // tax behavior variable to be set dynamically 
    // constructor
    public Alaska(String name) {
        //sets the state name to the given name
        setName(name);
    }
    
    //overrides the showTax function to match the Alaska state class.
    @Override
    public void showTax(Double value) {
        System.out.print("The sales tax on $" + value + " in " + getName() + " is ");
        System.out.printf("$%.2f", getBehavior().compute(value));
    }
    // sets the behavior dynamically by calling super setBehavior
    public void setBehavior(SalesTaxBehavior behavior) {
        super.setBehavior(behavior);
    }
}

class Indiana extends State {
    //SevenPercent tax bhavior to match Indiana tax rate.
    // constructor, sets the name to given name.
    public Indiana(String name) {
        setName(name);
    }

    //overrides the showTax function to match Indiana state class.
    @Override
    public void showTax(Double value) {
        System.out.print("The sales tax on $" + value + " in " + getName() + " is ");
        System.out.printf("$%.2f", getBehavior().compute(value));
    }
    // dynamically sets the behavior by calling super's set behavior method
    public void setBehavior(SalesTaxBehavior behavior) {
        super.setBehavior(behavior);
    }
}

// adding Hawaii state 
class Hawaii extends State {

    public Hawaii(String name) {
        setName(name);
    }
    
    //overriding showTax method to display specific name and tax.
    @Override
    public void showTax(Double value) {
        System.out.print("The sales tax on $" + value + " in " + getName() + " is ");
        System.out.printf("$%.2f", getBehavior().compute(value));
    }

    // calls the super method to set the behavior dynamically
    public void setBehavior(SalesTaxBehavior behavior) {
        super.setBehavior(behavior);
    }
}
