package frc.robot.Commands;

import frc.robot.Subsystems.AlgaeBalls;

public class MiddleAuto extends CommandGroup{

private final AlgaeBalls algae = new AlgaeBalls();

    public MiddleAuto() {
        
        addSequential(new Shoot());
        addSequential(new Pick());
                
               // addRequirements(Subsystem);
            }
           
            private void addSequential(Shoot shoot) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'addSequential'");
            }
        
            public class Shoot extends MiddleAuto {
       
        public Shoot() {
        // requires(???);
}    

        protected void initialize() {
    //ShootAlgaeBalls();
}
        protected void excecute() {      
        }
        
        protected boolean isFinished() {
            return true;
        }

        protected void end() { 
        }

        protected void interrupted() {
            end();
        }

}


public class Pick extends MiddleAuto {
    public Pick() {
       // requires(???);
    }

protected void initialize() {
   // PickAlgaeBalls();
}

protected boolean isFinished() {
    return true;
}

protected void end() {

}
protected void interrupted() {
    end();
}

}


}


