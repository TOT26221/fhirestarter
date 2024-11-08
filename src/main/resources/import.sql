INSERT INTO na_narrative (id, na_div, na_status) VALUES ('98f', '<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p><b>Generated Narrative</b></p><div style=\\\"display: inline-block; background-color: #d9e0e7; padding: 6px; margin: 4px; border: 1px solid #8da1b4; border-radius: 5px; line-height: 60%\\\"><p style=\\\"margin-bottom: 0px\\\">Resource &quot;f005&quot; </p></div><p><b>identifier</b>: id: 118265112 (OFFICIAL), id: 191REW8WE916 (USUAL)</p><p><b>name</b>: Langeveld Anne (OFFICIAL)</p><p><b>telecom</b>: ph: 0205563847(WORK), <a href=\\\"mailto:a.langeveld@bmc.nl\\\">a.langeveld@bmc.nl</a>, fax: 0205668916(WORK)</p><p><b>address</b>: Galapagosweg 9 Amsterdam 1105 AZ NLD (WORK)</p><p><b>gender</b>: female</p><p><b>birthDate</b>: 1959-03-11</p><p><b>communication</b>: France <span style=\\\"background: LightGoldenRodYellow; margin: 4px; border: 1px solid khaki\\\"> (<a href=\\\"http://terminology.hl7.org/3.1.0/CodeSystem-v3-ietf3066.html\\\">Tags for the Identification of Languages</a>#fr)</span></p></div>', 'generated');
INSERT INTO pr_practitioner (id, pr_active, pr_gender, pr_birthdate, dr_narrative_id) VALUES ('f005', true, 'female', '1959-03-11', '98f');

INSERT INTO i_identifier (i_pr_id, id, i_system, i_use, i_value) VALUES ('f005', 'er54', 'urn:oid:2.16.528.1.1007.3', 'official', '118265112');
INSERT INTO i_identifier (i_pr_id, id, i_system, i_use, i_value) VALUES ('f005', 'er36', 'urn:oid:2.16.840.1.113883.2.4.6.3', 'usual', '191REW8WE916');

INSERT INTO hn_humanname (hn_family, hn_pr_id, hn_use, id) VALUES ('Anna', 'f005', 'official', '23e');
INSERT INTO given_humanname (id, given) VALUES ('23e', 'Langeveld');
INSERT INTO suffix_humanname (id, suffix) VALUES ('23e', 'MD');

INSERT INTO cp_contactpoint (cp_pr_id, cp_system, cp_use, cp_value, id) VALUES ('f005', 'phone', 'work', '0205563847', '47zr');
INSERT INTO cp_contactpoint (cp_pr_id, cp_system, cp_use, cp_value, id) VALUES ('f005', 'email', 'work', 'a.langeveld@bmc.nl', 'nl34');
INSERT INTO cp_contactpoint (cp_pr_id, cp_system, cp_use, cp_value, id) VALUES ('f005', 'fax', 'work', '0205668916', '16ou');

INSERT INTO a_address (a_city, a_country, a_postalcode, a_use, id, a_pr_id) VALUES ('Amsterdam', 'NLD', '1105 AZ', 'work', '43edfg', 'f005');
INSERT INTO at_attachment (id, at_content_type, at_pr_id, at_data) VALUES ('re4','jpeg', 'f005', 'data:image/jpeg;base64,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');

INSERT INTO pe_period (pe_start, id) VALUES ('2014-07-09 06:00:00','pe1');
INSERT INTO pe_period (pe_start, id) VALUES ('2014-07-09 06:00:00','pe2');

INSERT INTO q_qualification (id, q_pr_id ,q_p_id ) VALUES ('qu1', 'f005', 'pe1');
INSERT INTO q_qualification (id, q_pr_id ,q_p_id ) VALUES ('qu2', 'f005', 'pe2');

INSERT INTO cc_codeableconcept (id, cc_text) VALUES ('3PCC', 'MD');
INSERT INTO cc_codeableconcept (id, cc_text) VALUES ('PR2', 'PhD');

INSERT INTO co_coding (id, co_system, co_code, co_display) VALUES ('co1', 'urn:ietf:bcp:47', 'fr', 'France');
INSERT INTO co_coding (co_user_selected, co_cc_id, co_code, co_display, co_system, id) VALUES (true, '3PCC', '677091', 'MD', 'https://fhir.cerner.com/ec2458f2-1e24-41c8-b71b-0e701af7583d/codeSet/29600', 'co2');
INSERT INTO co_coding (co_user_selected, co_cc_id, co_code, co_display, co_system, id) VALUES (true, 'PR2', '677079', 'PhD', 'https://fhir.cerner.com/ec2458f2-1e24-41c8-b71b-0e701af7583d/codeSet/29600', 'co3');

INSERT INTO p_patient (`id`, `p_active`,`p_deceasedboolean`, `p_gender`) VALUES ('643221yu', false, true, 'other');

INSERT INTO a_address_line (address_id, line) VALUES('43edfg','Galapagosweg 9');

INSERT INTO `fhirstarter`.`re_reference` (`id`, `re_ref`) VALUES ('rfen1', 'Encounter/f203');
INSERT INTO `fhirstarter`.`re_reference` (`id`, `re_display`, `re_ref`) VALUES ('rfen3', 'Roel', 'Patient/f201');

INSERT INTO `fhirstarter`.`en_encounter` (`en_part_of_re_id`, `id`, `en_status`, `en_re_id`, `dr_narrative_id`) VALUES ('rfen1', 'f201', 'finished', 'rfen3', '98f');
INSERT INTO `fhirstarter`.`i_identifier` (`i_en_id`, `i_value`, `id`, `i_use`) VALUES ('f201', 'Encounter_Roel_20130404', 'ien1', 'temp');
INSERT INTO `fhirstarter`.`re_reference` (`id`, `re_ref`) VALUES ('rfpt1', 'Practitioner/f201');

INSERT INTO `fhirstarter`.`pa_participant` (`en_pa_id`, `id`, `pa_re_id`) VALUES ('f201', 'pt1', 'rfpt1');
INSERT INTO `fhirstarter`.`re_reference` (`en_app_re_id`, `id`, `re_ref`) VALUES ('f201', 'rfen2', 'Appointment/example');
INSERT INTO `fhirstarter`.`cc_codeableconcept` (`en_cc_id`, `id`) VALUES ('f201', '2cc');
INSERT INTO `fhirstarter`.`co_coding` (`co_cc_id`, `co_code`, `co_display`, `co_system`, `id`) VALUES ('2cc', '11429006', 'Consultation', 'http://snomed.info/sct', '3co');-- diagnosisn 1
INSERT INTO `fhirstarter`.`cc_codeableconcept` (`id`) VALUES ( '3cc');

INSERT INTO `fhirstarter`.`co_coding` (`co_cc_id`, `co_code`, `co_display`, `co_system`, `id`) VALUES ('3cc', 'AD', 'Admission diagnosis', 'http://terminology.hl7.org/CodeSystem/diagnosis-role', '4co');
INSERT INTO `fhirstarter`.`re_reference` (`id`, `re_display`) VALUES ('rfdi1', 'Complications from Roels TPF chemotherapy on January 28th, 2013' );
INSERT INTO `fhirstarter`.`di_diagnosis` (`di_rank`, `di_cc_system`, `di_re_display`, `en_di_id`, `id`) VALUES ('2', '3cc', 'rfdi1', 'f201', 'di1');-- diagnosis 2
INSERT INTO `fhirstarter`.`cc_codeableconcept` (`id`) VALUES ( '4cc');

INSERT INTO `fhirstarter`.`co_coding` (`co_cc_id`, `co_code`, `co_display`, `co_system`, `id`) VALUES ('4cc', 'CC', 'Chief complaint', 'http://terminology.hl7.org/CodeSystem/diagnosis-role', '5co');
INSERT INTO `fhirstarter`.`re_reference` (`id`, `re_display`) VALUES ('rfdi2', 'The patient is treated for a tumor');
INSERT INTO `fhirstarter`.`di_diagnosis` (`di_rank`, `di_cc_system`, `di_re_display`, `en_di_id`, `id`) VALUES ('1', '4cc', 'rfdi2', 'f201', 'di2');-- statushistory

INSERT INTO `fhirstarter`.`pe_period` (`id`, `pe_start`) VALUES ('pesh1', '2013-03-08');
INSERT INTO `fhirstarter`.`sh_status_history` (`en_sh_id`, `id`, `sh_period_id`, `sh_status`) VALUES ('f201', 'sh1', 'pesh1', 'in_progress');
INSERT INTO `fhirstarter`.`na_narrative` (id, na_div, na_status) VALUES (
                                                                            '98f',
                                                                            '<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\">' ||
                                                                            '<p><b>Generated Narrative</b></p>' ||
                                                                            '<div style=\\\"display: inline-block; background-color: #d9e0e7; padding: 6px; margin: 4px; border: 1px solid #8da1b4; border-radius: 5px; line-height: 60%\\\">' ||
                                                                            '<p style=\\\"margin-bottom: 0px\\\">Resource &quot;f005&quot; </p></div>' ||
                                                                            '<p><b>identifier</b>: id: 118265112 (OFFICIAL), id: 191REW8WE916 (USUAL)</p>' ||
                                                                            '<p><b>name</b>: Langeveld Anne (OFFICIAL)</p>' ||
                                                                            '<p><b>telecom</b>: ph: 0205563847(WORK), <a href=\\\"mailto:a.langeveld@bmc.nl\\\">a.langeveld@bmc.nl</a>, fax: 0205668916(WORK)</p>' ||
                                                                            '<p><b>address</b>: Galapagosweg 9 Amsterdam 1105 AZ NLD (WORK)</p>' ||
                                                                            '<p><b>gender</b>: female</p>' ||
                                                                            '<p><b>birthDate</b>: 1959-03-11</p>' ||
                                                                            '<p><b>communication</b>: France <span style=\\\"background: LightGoldenRodYellow; margin: 4px; border: 1px solid khaki\\\">' ||
                                                                            '(<a href=\\\"http://terminology.hl7.org/3.1.0/CodeSystem-v3-ietf3066.html\\\">Tags for the Identification of Languages</a>#fr)</span></p>' ||
                                                                            '</div>',
                                                                            'generated'
                                                                        );

-- Insert into the medication table
-- Insert into me_medication table
INSERT INTO me_medication (id, me_status)
VALUES ('med0336', 'active');

-- Insert into cc_codeableconcept and co_coding tables for form
INSERT INTO cc_codeableconcept (cc_text, id) VALUES ('Injection Solution (qualifier value)', 'form_cc_id');
INSERT INTO co_coding (co_code, co_display, co_system, id) VALUES ('385219001', 'Injection Solution (qualifier value)', 'http://snomed.info/sct', 'form_co_id');

-- Insert into cc_codeableconcept and co_coding tables for ingredient 1
INSERT INTO cc_codeableconcept (cc_text, id) VALUES (NULL, 'ingredient1_cc_id');
INSERT INTO co_coding (co_code, co_display, co_system, id) VALUES ('1114879', 'Dopamine', 'http://www.nlm.nih.gov/research/umls/rxnorm', 'ingredient1_co_id');

-- Insert into ra_ratio table for ingredient 1 strength
INSERT INTO ra_ratio (ra_nu_id, ra_de_id, id) VALUES ('numerator1_id', 'denominator1_id', 'ratio1_id');
INSERT INTO qua_quantity (qua_value, qua_system, qua_code, id) VALUES (400, 'http://unitsofmeasure.org', 'mg', 'numerator1_id');
INSERT INTO qua_quantity (qua_value, qua_system, qua_code, id) VALUES (500, 'http://unitsofmeasure.org', 'mL', 'denominator1_id');

-- Insert into cc_codeableconcept and co_coding tables for ingredient 2
INSERT INTO cc_codeableconcept (cc_text, id) VALUES (NULL, 'ingredient2_cc_id');
INSERT INTO co_coding (co_code, co_display, co_system, id) VALUES ('0264751010', 'Dextrose 5% injection USP', 'http://hl7.org/fhir/sid/ndc', 'ingredient2_co_id');

-- Insert into ra_ratio table for ingredient 2 strength
INSERT INTO ra_ratio (ra_nu_id, ra_de_id, id) VALUES ('numerator2_id', 'denominator2_id', 'ratio2_id');
INSERT INTO qua_quantity (qua_value, qua_system, qua_code, id) VALUES (5, 'http://unitsofmeasure.org', 'g', 'numerator2_id');
INSERT INTO qua_quantity (qua_value, qua_system, qua_code, id) VALUES (100, 'http://unitsofmeasure.org', 'mL', 'denominator2_id');

-- Insert into ba_batch table
INSERT INTO ba_batch (ba_lot_number, ba_expiration_date, id) VALUES ('AT 9035 ZU69', '2030-11-30T06:00:00.000Z', 'batch_id');

-- Link the batch to the medication
UPDATE me_medication SET me_ba_id = 'batch_id' WHERE id = 'med0336';