package es.upv.iei.application;

import java.util.List;

/**
 * Created by Connor on 25/11/2016.
 */
public interface Filtro {
    public List<Mobile> find(String modelo);
}
