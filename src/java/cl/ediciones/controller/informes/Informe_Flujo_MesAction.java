package cl.ediciones.controller.informes;

import cl.ediciones.model.Fecha_Actual;
import cl.ediciones.model.Informes;
import cl.ediciones.model.dao.FacturaDAO;
import cl.ediciones.model.dao.Forma_PagoDAO;
import cl.ediciones.model.dao.InformesDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Informe_Flujo_MesAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String mes = request.getParameter("mes");
        String ano = request.getParameter("ano");
        
        InformesDAO infDAO = new InformesDAO();
        Informes mes_cheque = infDAO.meses_Flujo_Mes_Cheque(mes, ano);
        Informes mes_cuota = infDAO.meses_Flujo_Mes_Cuota(mes, ano);
        Informes mes_letra = infDAO.meses_Flujo_Mes_Lera(mes, ano);
        
        if (mes_cheque == null) {
            mes_cheque = new Informes();
            mes_cheque.setMes_inicio(mes);
            mes_cheque.setMes_fin("0");
            mes_cheque.setAno("0");
        }
        
        if (mes_cuota == null) {
            mes_cuota = new Informes();
            mes_cuota.setMes_inicio(mes);
            mes_cuota.setMes_fin("0");
            mes_cuota.setAno("0");
        }
        
        if (mes_letra == null) {
            mes_letra = new Informes();
            mes_letra.setMes_inicio(mes);
            mes_letra.setMes_fin("0");
            mes_letra.setAno("0");
        }
        
        int mes_u = 0;
        if (Integer.parseInt(mes_cheque.getAno()) > Integer.parseInt(ano)) {
            String mes_p = "" + (Integer.parseInt(mes_cheque.getMes_fin()) + 12);
            mes_cheque.setMes_fin(mes_p);
        }
        if (Integer.parseInt(mes_cuota.getAno()) > Integer.parseInt(ano)) {
            String mes_p = "" + (Integer.parseInt(mes_cuota.getMes_fin()) + 12);
            mes_cuota.setMes_fin(mes_p);
        }
        if (Integer.parseInt(mes_letra.getAno()) > Integer.parseInt(ano)) {
            String mes_p = "" + (Integer.parseInt(mes_letra.getMes_fin()) + 12);
            mes_letra.setMes_fin(mes_p);
        }
        
        if (Integer.parseInt(mes_cheque.getMes_fin()) >= Integer.parseInt(mes_cuota.getMes_fin())) {
            if (Integer.parseInt(mes_cheque.getMes_fin()) >= Integer.parseInt(mes_letra.getMes_fin())) {
                mes_u = Integer.parseInt(mes_cheque.getMes_fin());
            } else {
                mes_u = Integer.parseInt(mes_letra.getMes_fin());
            }
        } else if (Integer.parseInt(mes_cuota.getMes_fin()) >= Integer.parseInt(mes_letra.getMes_fin())) {
            mes_u = Integer.parseInt(mes_cuota.getMes_fin());
        } else {
            mes_u = Integer.parseInt(mes_letra.getMes_fin());
        }
        
        Forma_PagoDAO fpDAO = new Forma_PagoDAO();
        ArrayList lista_formas_pago = fpDAO.traerTodos_Forma_Pago();
        
        FacturaDAO facDAO = new FacturaDAO();
        ArrayList lista_factura = facDAO.traerTodos_Factura_Emitidas_por_Mes_Ano(mes, ano);
        
        int mes_f = Integer.parseInt(mes);
        int ano_uso = Integer.parseInt(ano);
        
        ArrayList lista_meses = new ArrayList();
        for (int i = mes_f; i <= mes_u; i++) {
            if (i == 1) {
                mes = "ENE";
            } else if (i == 2) {
                mes = "FEB";
            } else if (i == 3) {
                mes = "MAR";
            } else if (i == 4) {
                mes = "ABR";
            } else if (i == 5) {
                mes = "MAY";
            } else if (i == 6) {
                mes = "JUN";
            } else if (i == 7) {
                mes = "JUL";
            } else if (i == 8) {
                mes = "AGO";
            } else if (i == 9) {
                mes = "SEP";
            } else if (i == 10) {
                mes = "OCT";
            } else if (i == 11) {
                mes = "NOV";
            } else if (i == 12) {
                mes = "DIC";
            } else if (i == 13) {
                mes = "ENE";
                ano_uso = Integer.parseInt(ano) + 1;
            } else if (i == 14) {
                mes = "FEB";
                ano_uso = Integer.parseInt(ano) + 1;
            } else if (i == 15) {
                mes = "MAR";
                ano_uso = Integer.parseInt(ano) + 1;
            } else if (i == 16) {
                mes = "ABR";
                ano_uso = Integer.parseInt(ano) + 1;
            } else if (i == 17) {
                mes = "MAY";
                ano_uso = Integer.parseInt(ano) + 1;
            } else if (i == 18) {
                mes = "JUN";
                ano_uso = Integer.parseInt(ano) + 1;
            } else if (i == 19) {
                mes = "JUL";
                ano_uso = Integer.parseInt(ano) + 1;
            } else if (i == 20) {
                mes = "AGO";
                ano_uso = Integer.parseInt(ano) + 1;
            } else if (i == 21) {
                mes = "SEP";
                ano_uso = Integer.parseInt(ano) + 1;
            } else if (i == 22) {
                mes = "OCT";
                ano_uso = Integer.parseInt(ano) + 1;
            } else if (i == 23) {
                mes = "NOV";
                ano_uso = Integer.parseInt(ano) + 1;
            } else if (i == 24) {
                mes = "DIC";
                ano_uso = Integer.parseInt(ano) + 1;
            }
            
            Fecha_Actual fecha = new Fecha_Actual();
            fecha.setMes(mes);
            fecha.setAno(String.valueOf(ano_uso));
            lista_meses.add(fecha);
        }
        
        request.setAttribute("lista_meses", lista_meses);
        request.setAttribute("mes_uso", mes_u);
        request.setAttribute("lista_formas_pago", lista_formas_pago);
        request.setAttribute("lista_factura", lista_factura);
        
        return mapping.findForward("InformeFlujo");
    }
}
