package at.spengergasse.fhirstarter.model;

public class Narrative extends Element{

    public enum  NarrativeStatusCode {generated, extensions, additional, empty}
    private NarrativeStatusCode status;
    private String div;
}
