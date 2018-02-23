package po.galaxy.domain;

public enum GalaxyType {
    SBBC{public String get() {return "SBbc";}}
    ,SB0{public String get() {return "SB0";}}
    ,SBA{public String get() {return "SBa";}}
    ,SBAB{public String get() {return "SBab";}}
    ,SBB{public String get() {return "SBb";}}
    ,SBBSB{public String get() {return "SBb(s)b";}}
    ,SBC{public String get() {return "SBc";}}
    ,SABRSB{public String get() {return "SAB(rs)b";}}
    ,SBCD{public String get() {return "SBcd";}}
    ,SBD{public String get() {return "SBd";}}
    ,SBDM{public String get() {return "SBdm";}}
    ,SBM{public String get() {return "SBm";}}
    ,SB{public String get() {return "SB";}}
    ,SBRAB{public String get() {return "SB(r)ab";}};
    public abstract String get();
}
