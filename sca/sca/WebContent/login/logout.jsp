<%--
    DELPHOS SERVIÇOS TÉCNICOS S/A
    PROJETO == CONTROLE DE ACESSO ==
--%>
<%
    String uid = (String) session.getAttribute("corporativo_uid");
    session.removeAttribute("corporativo_uid");
    session.removeAttribute("corporativo_usuario");
    session.removeAttribute("corporativo_empresa");
    session.removeAttribute("corporativo_delphos");
    session.removeAttribute("corporativo_sistema");
    session.removeAttribute("corporativo_token");

    try {
        session.invalidate();

        if (uid != null) {
		    Cookie ck = new Cookie(uid, "x");
		    ck.setVersion(0);
		    ck.setPath("/");
		    response.addCookie(ck);
        }
    
    } catch (IllegalArgumentException iae) {
    	//e.printStackTrace();
    } finally {
	    response.sendRedirect("/sca");
    }
%>
