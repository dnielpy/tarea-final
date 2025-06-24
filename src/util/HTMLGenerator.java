package util;

import entidades.CMF;
import entidades.personal.Paciente;
import entidades.registros.Analisis;
import entidades.registros.Visita;

public class HTMLGenerator {

    public static String generarHTMLReporte(CMF cmf) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append(
                "<html lang='es'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>Reporte CMF</title>");
        html.append("<style>");
        html.append(
                "body { font-family: 'Arial', sans-serif; margin: 0; padding: 0; background-color: #f0f2f5; color: #333333; }");
        html.append("header { background-color: #4a00e0; color: white; padding: 20px 0; text-align: center; }");
        html.append("header h1 { margin: 0; font-size: 2.5em; }");
        html.append(
                "nav { background-color: #8e2de2; color: white; display: flex; justify-content: center; padding: 10px 0; }");
        html.append("nav a { color: white; text-decoration: none; margin: 0 15px; font-size: 1.2em; }");
        html.append("nav a:hover { color: #b388ff; }");
        html.append(
                "section { display: none; margin: 20px auto; max-width: 900px; background: white; border-radius: 8px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
        html.append(
                "section.active { display: block; }");
        html.append(
                "section h2 { background-color: #8e2de2; color: white; margin: 0; padding: 15px; border-radius: 8px 8px 0 0; }");
        html.append("ul { list-style: none; padding: 20px; margin: 0; }");
        html.append(
                "li { background: #f0f2f5; margin: 10px 0; padding: 15px; border-radius: 5px; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
        html.append("li:nth-child(odd) { background: #e0e0e0; }");
        html.append(
                "button { background-color: #4a00e0; color: white; border: none; padding: 5px 10px; border-radius: 5px; cursor: pointer; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
        html.append("button:hover { background-color: #b388ff; }");
        html.append(
                "footer { text-align: center; padding: 10px; background-color: #4a00e0; color: white; margin-top: 20px; }");
        html.append("footer p { margin: 0; font-size: 0.9em; }");
        html.append(
                ".modal { display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2); z-index: 1000; }");
        html.append(".modal.active { display: block; }");
        html.append(".modal-header { font-size: 1.5em; margin-bottom: 10px; color: #4a00e0; }");
        html.append(
                ".modal-close { background: black; color: white; border: none; font-size: 1.2em; cursor: pointer; position: absolute; top: 10px; right: 10px; padding: 5px 10px; border-radius: 5px; }");
        html.append(".modal-close:hover { background: #333333; }");
        html.append(".modal-content { margin-top: 40px; }");
        html.append(
                ".overlay { display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.5); z-index: 999; }");
        html.append(".overlay.active { display: block; }");
        html.append(
                ".search-bar { margin: 20px auto; max-width: 900px; display: flex; justify-content: center; }");
        html.append(
                ".search-bar input { width: 100%; max-width: 600px; padding: 10px; border: 1px solid #ccc; border-radius: 5px; font-size: 1em; }");
        html.append("</style>");
        html.append("<script>");
        html.append("function showSection(sectionId) {");
        html.append("  document.querySelectorAll('section').forEach(section => section.classList.remove('active'));");
        html.append("  document.getElementById(sectionId).classList.add('active');");
        html.append("}");
        html.append("function openModal(modalId) {");
        html.append("  document.getElementById(modalId).classList.add('active');");
        html.append("  document.getElementById('overlay').classList.add('active');");
        html.append("}");
        html.append("function closeModal(modalId) {");
        html.append("  document.getElementById(modalId).classList.remove('active');");
        html.append("  document.getElementById('overlay').classList.remove('active');");
        html.append("}");
        html.append("function filterItems(sectionId, inputId) {");
        html.append("  const query = document.getElementById(inputId).value.toLowerCase();");
        html.append("  const items = document.querySelectorAll(`#${sectionId} ul li`);");
        html.append("  items.forEach(item => {");
        html.append("    const text = item.textContent.toLowerCase();");
        html.append("    item.style.display = text.includes(query) ? '' : 'none';");
        html.append("  });");
        html.append("}");
        html.append("</script>");
        html.append("</head><body>");

        // Overlay
        html.append("<div id='overlay' class='overlay'></div>");

        // Header
        html.append("<header><h1>Reporte del Consultorio M&eacute;dico Familiar</h1></header>");

        // Navigation Bar
        html.append("<nav>");
        html.append("<a href='#' onclick=\"showSection('pacientes')\">Pacientes</a>");
        html.append("<a href='#' onclick=\"showSection('visitas')\">Visitas</a>");
        html.append("<a href='#' onclick=\"showSection('analisis')\">An&aacute;lisis</a>");
        html.append("</nav>");

        // Search Bar
        html.append("<div class='search-bar'>");
        html.append(
                "<input id='searchInput' type='text' placeholder='Buscar...' oninput=\"filterItems(document.querySelector('section.active').id, 'searchInput')\">");
        html.append("</div>");

        // Pacientes Section
        html.append("<section id='pacientes' class='active'><h2>Pacientes</h2><ul>");
        for (Paciente paciente : cmf.getPacientes()) {
            String modalId = "modalPaciente" + paciente.getHistoriaClinica().getId();
            html.append("<li>")
                    .append("<div>")
                    .append("<strong>ID:</strong> ").append(paciente.getHistoriaClinica().getId()).append("<br>")
                    .append("<strong>Nombre:</strong> ").append(paciente.getNombreYApellidos().replace("á", "&aacute;")
                            .replace("é", "&eacute;").replace("í", "&iacute;").replace("ó", "&oacute;")
                            .replace("ú", "&uacute;").replace("ñ", "&ntilde;").replace("Ñ", "&Ntilde;"))
                    .append("<br>")
                    .append("<strong>CI:</strong> ").append(paciente.getCI())
                    .append("</div>")
                    .append("<button onclick=\"openModal('").append(modalId).append("')\">Ver</button>")
                    .append("</li>");

            // Modal for patient details
            html.append("<div id='").append(modalId).append("' class='modal'>")
                    .append("<button class='modal-close' onclick=\"closeModal('").append(modalId)
                    .append("')\">&times;</button>")
                    .append("<div class='modal-content'>")
                    .append("<div class='modal-header'>Informaci&oacute;n del Paciente</div>")
                    .append("<p><strong>ID:</strong> ").append(paciente.getHistoriaClinica().getId()).append("</p>")
                    .append("<p><strong>Nombre:</strong> ")
                    .append(paciente.getNombreYApellidos().replace("á", "&aacute;")
                            .replace("é", "&eacute;").replace("í", "&iacute;").replace("ó", "&oacute;")
                            .replace("ú", "&uacute;").replace("ñ", "&ntilde;").replace("Ñ", "&Ntilde;"))
                    .append("</p>")
                    .append("<p><strong>CI:</strong> ").append(paciente.getCI()).append("</p>")
                    .append("<p><strong>Direcci&oacute;n:</strong> ")
                    .append(paciente.getDireccion().replace("á", "&aacute;")
                            .replace("é", "&eacute;").replace("í", "&iacute;").replace("ó", "&oacute;")
                            .replace("ú", "&uacute;").replace("ñ", "&ntilde;").replace("Ñ", "&Ntilde;"))
                    .append("</p>")
                    .append("</div>")
                    .append("</div>");
        }
        html.append("</ul></section>");

        // Visitas Section
        html.append("<section id='visitas'><h2>Visitas</h2><ul>");
        for (Visita visita : cmf.obtenerListaVisitas()) {
            String modalId = "modalVisita" + visita.getId();
            html.append("<li>")
                    .append("<div>")
                    .append("<strong>ID:</strong> ").append(visita.getId()).append("<br>")
                    .append("<strong>Fecha:</strong> ").append(visita.getFecha()).append("<br>")
                    .append("<strong>Diagn&oacute;stico:</strong> ")
                    .append(visita.getDiagnostico().replace("á", "&aacute;")
                            .replace("é", "&eacute;").replace("í", "&iacute;").replace("ó", "&oacute;")
                            .replace("ú", "&uacute;").replace("ñ", "&ntilde;").replace("Ñ", "&Ntilde;"))
                    .append("</div>")
                    .append("<button onclick=\"openModal('").append(modalId).append("')\">Ver</button>")
                    .append("</li>");

            // Modal for visit details
            html.append("<div id='").append(modalId).append("' class='modal'>")
                    .append("<button class='modal-close' onclick=\"closeModal('").append(modalId)
                    .append("')\">&times;</button>")
                    .append("<div class='modal-content'>")
                    .append("<div class='modal-header'>Informaci&oacute;n de la Visita</div>")
                    .append("<p><strong>ID:</strong> ").append(visita.getId()).append("</p>")
                    .append("<p><strong>Fecha:</strong> ").append(visita.getFecha()).append("</p>")
                    .append("<p><strong>Diagnóstico:</strong> ").append(visita.getDiagnostico().replace("á", "&aacute;")
                            .replace("é", "&eacute;").replace("í", "&iacute;").replace("ó", "&oacute;")
                            .replace("ú", "&uacute;").replace("ñ", "&ntilde;").replace("Ñ", "&Ntilde;"))
                    .append("</p>")
                    .append("<p><strong>Tratamiento:</strong> ").append(visita.getTratamiento().replace("á", "&aacute;")
                            .replace("é", "&eacute;").replace("í", "&iacute;").replace("ó", "&oacute;")
                            .replace("ú", "&uacute;").replace("ñ", "&ntilde;").replace("Ñ", "&Ntilde;"))
                    .append("</p>")
                    .append("</div>")
                    .append("</div>");
        }
        html.append("</ul></section>");

        // Análisis Section
        html.append("<section id='analisis'><h2>An&aacute;lisis</h2><ul>");
        for (Analisis analisis : cmf.obtenerTodosLosAnalisis()) {
            String modalId = "modalAnalisis" + analisis.getId();
            html.append("<li>")
                    .append("<div>")
                    .append("<strong>ID:</strong> ").append(analisis.getId()).append("<br>")
                    .append("<strong>Tipo:</strong> ").append(analisis.getTipoDeAnalisis().replace("á", "&aacute;")
                            .replace("é", "&eacute;").replace("í", "&iacute;").replace("ó", "&oacute;")
                            .replace("ú", "&uacute;").replace("ñ", "&ntilde;").replace("Ñ", "&Ntilde;"))
                    .append("</div>")
                    .append("<button onclick=\"openModal('").append(modalId).append("')\">Ver</button>")
                    .append("</li>");

            // Modal for analysis details
            html.append("<div id='").append(modalId).append("' class='modal'>")
                    .append("<button class='modal-close' onclick=\"closeModal('").append(modalId)
                    .append("')\">&times;</button>")
                    .append("<div class='modal-content'>")
                    .append("<div class='modal-header'>Informaci&oacute;n del An&aacute;lisis</div>")
                    .append("<p><strong>ID:</strong> ").append(analisis.getId()).append("</p>")
                    .append("<p><strong>Tipo:</strong> ").append(analisis.getTipoDeAnalisis().replace("á", "&aacute;")
                            .replace("é", "&eacute;").replace("í", "&iacute;").replace("ó", "&oacute;")
                            .replace("ú", "&uacute;").replace("ñ", "&ntilde;").replace("Ñ", "&Ntilde;"))
                    .append("</p>")
                    .append("<p><strong>Fecha Orientado:</strong> ").append(analisis.getFechaOrientado()).append("</p>")
                    .append("<p><strong>Resultados:</strong> ")
                    .append(analisis.getResultados() != null
                            ? analisis.getResultados().replace("á", "&aacute;").replace("é", "&eacute;")
                                    .replace("í", "&iacute;").replace("ó", "&oacute;").replace("ú", "&uacute;")
                                    .replace("ñ", "&ntilde;").replace("Ñ", "&Ntilde;")
                            : "Pendiente")
                    .append("</p>")
                    .append("</div>")
                    .append("</div>");
        }
        html.append("</ul></section>");

        // Footer
        html.append(
                "<footer><p>&copy; 2023 Consultorio M&eacute;dico Familiar. Todos los derechos reservados.</p></footer>");

        html.append("</body></html>");

        return html.toString();
    }
}
