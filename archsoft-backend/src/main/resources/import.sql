INSERT INTO section (section_id,section_name) VALUES (nextval('section_id_seq'),'Diseño arquitectónico y toma de decisiones técnicas');
INSERT INTO section (section_id,section_name) VALUES (nextval('section_id_seq'),'Comunicación y documentación');
INSERT INTO section (section_id,section_name) VALUES (nextval('section_id_seq'),'Liderazgo y colaboración');
INSERT INTO section (section_id,section_name) VALUES (nextval('section_id_seq'),'Habilidades de resolución de problemas');
INSERT INTO section (section_id,section_name) VALUES (nextval('section_id_seq'),'Adaptabilidad y aprendizaje continuo');

INSERT INTO metric (metric_id,metric_name,metric_section) VALUES (nextval('metric_id_seq'),'Porcentaje de requisitos satisfechos',1);
INSERT INTO metric (metric_id,metric_name,metric_section) VALUES (nextval('metric_id_seq'),'Toma de decisiones técnicas debidamente justificadas',1);
INSERT INTO metric (metric_id,metric_name,metric_section) VALUES (nextval('metric_id_seq'),'Identificación y elección de patrones arquitectónicos',1);

INSERT INTO metric (metric_id,metric_name,metric_section) VALUES (nextval('metric_id_seq'),'Calidad de documentación',2);
INSERT INTO metric (metric_id,metric_name,metric_section) VALUES (nextval('metric_id_seq'),'Efectividad en la comunicación',2);

INSERT INTO metric (metric_id,metric_name,metric_section) VALUES (nextval('metric_id_seq'),'Satisfacción del equipo sobre el liderazgo técnico del arquitecto',3);
INSERT INTO metric (metric_id,metric_name,metric_section) VALUES (nextval('metric_id_seq'),'Porcentaje de objetivos arquitectónicos logrados',3);

INSERT INTO metric (metric_id,metric_name,metric_section) VALUES (nextval('metric_id_seq'),'Tasa de resolución efectiva de problemas',4);
INSERT INTO metric (metric_id,metric_name,metric_section) VALUES (nextval('metric_id_seq'),'Porcentaje de riesgos anticipados y mitigados',4);
INSERT INTO metric (metric_id,metric_name,metric_section) VALUES (nextval('metric_id_seq'),'Calidad de las soluciones arquitectónicas aportadas',4);

INSERT INTO metric (metric_id,metric_name,metric_section) VALUES (nextval('metric_id_seq'),'Porcentaje de cambios arquitectónicos exitosamente integrados',5);
INSERT INTO metric (metric_id,metric_name,metric_section) VALUES (nextval('metric_id_seq'),'Tasa de adopción de nuevas tecnologías y prácticas arquitectónicas',5);
