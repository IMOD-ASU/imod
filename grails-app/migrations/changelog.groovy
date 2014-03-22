databaseChangeLog = {

	changeSet(author: "Shruti (generated)", id: "1369528241065-1") {
		createTable(tableName: "action_word") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "action_wordPK")
			}

			column(name: "action_word", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "category_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "domain_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "visibility", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-2") {
		createTable(tableName: "audience") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "audiencePK")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-3") {
		createTable(tableName: "component_designation") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "component_desPK")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-4") {
		createTable(tableName: "content") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "contentPK")
			}

			column(name: "learning_objective_id", type: "int8")

			column(name: "priority_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "topic_title", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-5") {
		createTable(tableName: "content_content_resource") {
			column(name: "content_resources_id", type: "int8")

			column(name: "content_resource_id", type: "int8")
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-6") {
		createTable(tableName: "content_hierarchy") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "content_hieraPK")
			}

			column(name: "leaf_content_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "parent_content_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-7") {
		createTable(tableName: "content_priority_code") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "content_priorPK")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-8") {
		createTable(tableName: "content_resource") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "content_resouPK")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "type_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-9") {
		createTable(tableName: "content_resource_type") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "content_resouPK")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-10") {
		createTable(tableName: "course_component_code") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "course_componPK")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "designation_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-11") {
		createTable(tableName: "course_policy") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "course_policyPK")
			}

			column(name: "category_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-12") {
		createTable(tableName: "course_policy_category") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "course_policyPK")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-13") {
		createTable(tableName: "domain_category") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "domain_categoPK")
			}

			column(name: "domain_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-14") {
		createTable(tableName: "help") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "helpPK")
			}

			column(name: "tab_field_id", type: "int4") {
				constraints(nullable: "false")
			}

			column(name: "tab_id", type: "int4") {
				constraints(nullable: "false")
			}

			column(name: "text", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-15") {
		createTable(tableName: "imod") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "imodPK")
			}

			column(name: "course_location", type: "varchar(255)")

			column(name: "course_policy_id", type: "int8")

			column(name: "course_semester", type: "varchar(255)")

			column(name: "credit_hours", type: "int4")

			column(name: "imod_number", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "number_of_seats", type: "int4")

			column(name: "overview", type: "varchar(255)")

			column(name: "owner_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "piechart_id", type: "int8")

			column(name: "repeats_id", type: "int8")

			column(name: "repeats_every_id", type: "int8")

			column(name: "schedule_id", type: "int8")

			column(name: "schedule_date_id", type: "int8")

			column(name: "subject_area", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "time_ratio", type: "varchar(255)")

			column(name: "url", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-16") {
		createTable(tableName: "imod_audience") {
			column(name: "imod_audience_id", type: "int8")

			column(name: "audience_id", type: "int8")
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-17") {
		createTable(tableName: "imod_content") {
			column(name: "imod_contents_id", type: "int8")

			column(name: "content_id", type: "int8")
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-18") {
		createTable(tableName: "imod_course_component_code") {
			column(name: "imod_course_components_id", type: "int8")

			column(name: "course_component_code_id", type: "int8")
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-19") {
		createTable(tableName: "imod_instructor") {
			column(name: "imod_instructors_id", type: "int8")

			column(name: "instructor_id", type: "int8")
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-20") {
		createTable(tableName: "imod_user") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "imod_userPK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bool") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bool") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bool") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bool") {
				constraints(nullable: "false")
			}

			column(name: "profile_id", type: "int8")

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-21") {
		createTable(tableName: "imod_user_imods") {
			column(name: "imod_user_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "owner_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "imod_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-22") {
		createTable(tableName: "imod_user_role") {
			column(name: "role_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "imod_user_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-23") {
		createTable(tableName: "instructor") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "instructorPK")
			}

			column(name: "created_by_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)")

			column(name: "first_name", type: "varchar(255)")

			column(name: "last_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "location", type: "varchar(255)")

			column(name: "middle_initial", type: "varchar(255)")

			column(name: "office_hours", type: "varchar(255)")

			column(name: "phone_number", type: "varchar(255)")

			column(name: "web_page", type: "varchar(255)")
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-24") {
		createTable(tableName: "instructor_phone") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "instructor_phPK")
			}

			column(name: "instructor_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "number", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-25") {
		createTable(tableName: "learning_domain") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "learning_domaPK")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-26") {
		createTable(tableName: "learning_objective") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "learning_objePK")
			}

			column(name: "action_word_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "complete_learning_objective", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "condition", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "content_id", type: "int8")

			column(name: "criteria", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "criteria_type_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "domain_category_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "imod_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "indicator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "learning_domain_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "performance", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-27") {
		createTable(tableName: "learning_objective_criteria_type") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "learning_objePK")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-28") {
		createTable(tableName: "piechart") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "piechartPK")
			}

			column(name: "priority_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-29") {
		createTable(tableName: "registration_code") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "registration_PK")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-30") {
		createTable(tableName: "role") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-31") {
		createTable(tableName: "schedule") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "schedulePK")
			}

			column(name: "end_date", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "end_occurences", type: "int4")

			column(name: "friday", type: "int4")

			column(name: "monday", type: "int4")

			column(name: "repeats_days", type: "varchar(255)")

			column(name: "saturday", type: "int4")

			column(name: "start_date", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "sunday", type: "int4")

			column(name: "thursday", type: "int4")

			column(name: "tuesday", type: "int4")

			column(name: "wednesday", type: "int4")
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-32") {
		createTable(tableName: "schedule_date") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "schedule_datePK")
			}

			column(name: "date", type: "timestamp") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-33") {
		createTable(tableName: "schedule_repeats") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "schedule_repePK")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-34") {
		createTable(tableName: "schedule_repeats_every") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "schedule_repePK")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-35") {
		createTable(tableName: "user_profile") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "user_profilePK")
			}

			column(name: "city", type: "varchar(255)")

			column(name: "country", type: "varchar(255)")

			column(name: "email", type: "varchar(255)")

			column(name: "first_name", type: "varchar(255)")

			column(name: "last_name", type: "varchar(255)")

			column(name: "middle_initial", type: "varchar(255)")

			column(name: "organization", type: "varchar(255)")

			column(name: "state", type: "varchar(255)")

			column(name: "street_address", type: "varchar(255)")

			column(name: "theme_code", type: "varchar(255)")

			column(name: "zip", type: "varchar(255)")
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-36") {
		addPrimaryKey(columnNames: "imod_user_id, owner_id", tableName: "imod_user_imods")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-37") {
		addPrimaryKey(columnNames: "role_id, imod_user_id", constraintName: "imod_user_rolPK", tableName: "imod_user_role")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-38") {
		addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "action_word", constraintName: "FK5E677373E115253", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain_category", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-39") {
		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "action_word", constraintName: "FK5E6773735CF2D06D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "learning_domain", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-40") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "action_word", constraintName: "FK5E6773731F617BA8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod_user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-41") {
		addForeignKeyConstraint(baseColumnNames: "learning_objective_id", baseTableName: "content", constraintName: "FK38B7347974DC7C88", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "learning_objective", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-42") {
		addForeignKeyConstraint(baseColumnNames: "priority_id", baseTableName: "content", constraintName: "FK38B734791843770B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "content_priority_code", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-43") {
		addForeignKeyConstraint(baseColumnNames: "content_resource_id", baseTableName: "content_content_resource", constraintName: "FKDDFF481A13AADDF8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "content_resource", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-44") {
		addForeignKeyConstraint(baseColumnNames: "content_resources_id", baseTableName: "content_content_resource", constraintName: "FKDDFF481AFB03311F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "content", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-45") {
		addForeignKeyConstraint(baseColumnNames: "leaf_content_id", baseTableName: "content_hierarchy", constraintName: "FKF9B23DEF9DEF37C6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "content", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-46") {
		addForeignKeyConstraint(baseColumnNames: "parent_content_id", baseTableName: "content_hierarchy", constraintName: "FKF9B23DEF40846FA", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "content", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-47") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "content_resource", constraintName: "FKF7EAB914D5F1586C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "content_resource_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-48") {
		addForeignKeyConstraint(baseColumnNames: "designation_id", baseTableName: "course_component_code", constraintName: "FKBB15D493421B9DD2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "component_designation", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-49") {
		addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "course_policy", constraintName: "FK655E7DD67AAAC9FC", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "course_policy_category", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-50") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "course_policy", constraintName: "FK655E7DD61F617BA8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod_user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-51") {
		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "domain_category", constraintName: "FK92BD36195CF2D06D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "learning_domain", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-52") {
		addForeignKeyConstraint(baseColumnNames: "course_policy_id", baseTableName: "imod", constraintName: "FK3161F9D4B01B06", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "course_policy", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-53") {
		addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "imod", constraintName: "FK3161F98B482BC0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod_user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-54") {
		addForeignKeyConstraint(baseColumnNames: "piechart_id", baseTableName: "imod", constraintName: "FK3161F9456C38AF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "piechart", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-55") {
		addForeignKeyConstraint(baseColumnNames: "repeats_every_id", baseTableName: "imod", constraintName: "FK3161F97D973395", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "schedule_repeats_every", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-56") {
		addForeignKeyConstraint(baseColumnNames: "repeats_id", baseTableName: "imod", constraintName: "FK3161F921703E4E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "schedule_repeats", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-57") {
		addForeignKeyConstraint(baseColumnNames: "schedule_date_id", baseTableName: "imod", constraintName: "FK3161F9CCCA0E3E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "schedule_date", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-58") {
		addForeignKeyConstraint(baseColumnNames: "schedule_id", baseTableName: "imod", constraintName: "FK3161F92495468F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "schedule", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-59") {
		addForeignKeyConstraint(baseColumnNames: "audience_id", baseTableName: "imod_audience", constraintName: "FK55C6E18AB453736F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "audience", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-60") {
		addForeignKeyConstraint(baseColumnNames: "imod_audience_id", baseTableName: "imod_audience", constraintName: "FK55C6E18AA6A5E09E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-61") {
		addForeignKeyConstraint(baseColumnNames: "content_id", baseTableName: "imod_content", constraintName: "FK29173433E7922845", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "content", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-62") {
		addForeignKeyConstraint(baseColumnNames: "imod_contents_id", baseTableName: "imod_content", constraintName: "FK291734335905CFC8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-63") {
		addForeignKeyConstraint(baseColumnNames: "course_component_code_id", baseTableName: "imod_course_component_code", constraintName: "FKF176C6CD53F9C921", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "course_component_code", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-64") {
		addForeignKeyConstraint(baseColumnNames: "imod_course_components_id", baseTableName: "imod_course_component_code", constraintName: "FKF176C6CD1E9008F4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-65") {
		addForeignKeyConstraint(baseColumnNames: "imod_instructors_id", baseTableName: "imod_instructor", constraintName: "FK6A93EA3B0AD2238", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-66") {
		addForeignKeyConstraint(baseColumnNames: "instructor_id", baseTableName: "imod_instructor", constraintName: "FK6A93EA3E444078F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "instructor", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-67") {
		addForeignKeyConstraint(baseColumnNames: "profile_id", baseTableName: "imod_user", constraintName: "FKAADB9651AB292E7A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user_profile", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-68") {
		addForeignKeyConstraint(baseColumnNames: "imod_id", baseTableName: "imod_user_imods", constraintName: "FK2643162C265DD5CF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-69") {
		addForeignKeyConstraint(baseColumnNames: "imod_user_id", baseTableName: "imod_user_imods", constraintName: "FK2643162CFC17B62", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod_user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-70") {
		addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "imod_user_imods", constraintName: "FK2643162C16C5D2D5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-71") {
		addForeignKeyConstraint(baseColumnNames: "imod_user_id", baseTableName: "imod_user_role", constraintName: "FK6C9AEDA4FC17B62", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod_user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-72") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "imod_user_role", constraintName: "FK6C9AEDA45B9E8AF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-73") {
		addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "instructor", constraintName: "FK5329109D9C7E1385", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod_user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-74") {
		addForeignKeyConstraint(baseColumnNames: "instructor_id", baseTableName: "instructor_phone", constraintName: "FKDC7074CE444078F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "instructor", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-75") {
		addForeignKeyConstraint(baseColumnNames: "action_word_id", baseTableName: "learning_objective", constraintName: "FKA2655958706D4ABC", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "action_word", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-76") {
		addForeignKeyConstraint(baseColumnNames: "content_id", baseTableName: "learning_objective", constraintName: "FKA2655958E7922845", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "content", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-77") {
		addForeignKeyConstraint(baseColumnNames: "criteria_type_id", baseTableName: "learning_objective", constraintName: "FKA2655958257080DF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "learning_objective_criteria_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-78") {
		addForeignKeyConstraint(baseColumnNames: "domain_category_id", baseTableName: "learning_objective", constraintName: "FKA2655958E3247F58", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain_category", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-79") {
		addForeignKeyConstraint(baseColumnNames: "imod_id", baseTableName: "learning_objective", constraintName: "FKA2655958265DD5CF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "imod", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-80") {
		addForeignKeyConstraint(baseColumnNames: "learning_domain_id", baseTableName: "learning_objective", constraintName: "FKA26559585821708C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "learning_domain", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-81") {
		addForeignKeyConstraint(baseColumnNames: "priority_id", baseTableName: "piechart", constraintName: "FKD6EF3DD21843770B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "content_priority_code", referencesUniqueColumn: "false")
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-82") {
		createIndex(indexName: "username_unique_1369528240931", tableName: "imod_user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-83") {
		createIndex(indexName: "authority_unique_1369528240948", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "Shruti (generated)", id: "1369528241065-84") {
		createSequence(sequenceName: "hibernate_sequence")
	}
}
