package main

import (
	"github.com/input-api/mcp-server/config"
	"github.com/input-api/mcp-server/models"
	tools_registercustomer "github.com/input-api/mcp-server/tools/registercustomer"
	tools_id "github.com/input-api/mcp-server/tools/id"
	tools_com_broadleafcommerce_inventory_advanced_domain_inventoryimpl "github.com/input-api/mcp-server/tools/com_broadleafcommerce_inventory_advanced_domain_inventoryimpl"
	tools_changepassword "github.com/input-api/mcp-server/tools/changepassword"
	tools_update_navigation "github.com/input-api/mcp-server/tools/update_navigation"
	tools_savephone "github.com/input-api/mcp-server/tools/savephone"
	tools_add "github.com/input-api/mcp-server/tools/add"
	tools_owningclass "github.com/input-api/mcp-server/tools/owningclass"
	tools_forgotpassword "github.com/input-api/mcp-server/tools/forgotpassword"
	tools_myaccount "github.com/input-api/mcp-server/tools/myaccount"
	tools_addlsectionkey "github.com/input-api/mcp-server/tools/addlsectionkey"
	tools_uploadasset "github.com/input-api/mcp-server/tools/uploadasset"
	tools_delete "github.com/input-api/mcp-server/tools/delete"
	tools_login "github.com/input-api/mcp-server/tools/login"
	tools_collectionfield "github.com/input-api/mcp-server/tools/collectionfield"
	tools_logjavascripterror "github.com/input-api/mcp-server/tools/logjavascripterror"
	tools_registercustomersuccess "github.com/input-api/mcp-server/tools/registercustomersuccess"
	tools_makephonedefault "github.com/input-api/mcp-server/tools/makephonedefault"
	tools_product "github.com/input-api/mcp-server/tools/product"
	tools_forgotusername "github.com/input-api/mcp-server/tools/forgotusername"
	tools_sessiontimerreset "github.com/input-api/mcp-server/tools/sessiontimerreset"
	tools_translation "github.com/input-api/mcp-server/tools/translation"
	tools_sectionkey "github.com/input-api/mcp-server/tools/sectionkey"
	tools_resetpassword "github.com/input-api/mcp-server/tools/resetpassword"
	tools_api "github.com/input-api/mcp-server/tools/api"
	tools_propertyname "github.com/input-api/mcp-server/tools/propertyname"
	tools_selectize "github.com/input-api/mcp-server/tools/selectize"
	tools_update "github.com/input-api/mcp-server/tools/update"
	tools_viewphone "github.com/input-api/mcp-server/tools/viewphone"
	tools_sendresetpassword "github.com/input-api/mcp-server/tools/sendresetpassword"
	tools_deletephone "github.com/input-api/mcp-server/tools/deletephone"
	tools_sc_conflict "github.com/input-api/mcp-server/tools/sc_conflict"
)

func GetAll(cfg *config.APIConfig) []models.Tool {
	return []models.Tool{
		tools_registercustomer.CreateGet_registercustomerTool(cfg),
		tools_id.CreatePost_id_deleteTool(cfg),
		tools_id.CreateGet_id_collectionfield0_9__collectionitemid_viewTool(cfg),
		tools_com_broadleafcommerce_inventory_advanced_domain_inventoryimpl.CreateGet_com_broadleafcommerce_inventory_advanced_domain_inventoryimplTool(cfg),
		tools_changepassword.CreateGet_changepasswordTool(cfg),
		tools_changepassword.CreatePost_changepasswordTool(cfg),
		tools_update_navigation.CreateGet_update_navigationTool(cfg),
		tools_id.CreatePost_id_collectionfield0_9__selectize_addTool(cfg),
		tools_savephone.CreateGet_savephoneTool(cfg),
		tools_add.CreatePost_addTool(cfg),
		tools_add.CreateGet_addTool(cfg),
		tools_id.CreatePost_id_collectionfield0_9__collectionitemid_alternateid_deleteTool(cfg),
		tools_id.CreateGet_id_collectionfield0_9__collectionitemid_alternateid_viewTool(cfg),
		tools_owningclass.CreateGet_owningclass__collectionfield__selectTool(cfg),
		tools_id.CreateGet_id_collectionfield0_9__collectionitemid_alternateidTool(cfg),
		tools_id.CreatePost_id_collectionfield0_9__collectionitemid_alternateidTool(cfg),
		tools_forgotpassword.CreateGet_forgotpasswordTool(cfg),
		tools_id.CreatePost_id_collectionfield0_9__collectionitemid_sequenceTool(cfg),
		tools_myaccount.CreateGet_myaccount_phoneTool(cfg),
		tools_id.CreateGet_id_chooseassetTool(cfg),
		tools_id.CreatePost_id_uploadassetTool(cfg),
		tools_addlsectionkey.CreateGet_addlsectionkey_id_uploadassetTool(cfg),
		tools_uploadasset.CreatePost_uploadassetTool(cfg),
		tools_delete.CreatePost_deleteTool(cfg),
		tools_login.CreateGet_loginTool(cfg),
		tools_collectionfield.CreateGet_collectionfield__detailsTool(cfg),
		tools_registercustomer.CreateGet_registercustomerTool(cfg),
		tools_logjavascripterror.CreatePost_logjavascripterrorTool(cfg),
		tools_registercustomersuccess.CreateGet_registercustomersuccessTool(cfg),
		tools_id.CreateGet_id_collectionfield0_9_Tool(cfg),
		tools_id.CreatePost_id_collectionfield0_9__addemptyTool(cfg),
		tools_id.CreateGet_id_collectionfield_addTool(cfg),
		tools_makephonedefault.CreateGet_makephonedefaultTool(cfg),
		tools_id.CreateGet_idTool(cfg),
		tools_id.CreatePost_idTool(cfg),
		tools_product.CreateGet_product_productid_skusfieldname_generate_skusTool(cfg),
		tools_forgotusername.CreateGet_forgotusernameTool(cfg),
		tools_forgotusername.CreatePost_forgotusernameTool(cfg),
		tools_owningclass.CreateGet_owningclass__collectionfield__typeaheadTool(cfg),
		tools_id.CreateGet_id_collectionfield0_9__collectionitemidTool(cfg),
		tools_id.CreatePost_id_collectionfield0_9__collectionitemidTool(cfg),
		tools_id.CreatePost_id_duplicateTool(cfg),
		tools_sessiontimerreset.CreateGet_sessiontimerresetTool(cfg),
		tools_id.CreatePost_id_collectionfield0_9__collectionitemid_alternateid_sequenceTool(cfg),
		tools_id.CreatePost_id_collectionfield0_9__collectionitemid_deleteTool(cfg),
		tools_id.CreatePost_id_collectionfield0_9__add_collectionitemid_verifyTool(cfg),
		tools_translation.CreateGet_translationTool(cfg),
		tools_id.CreatePost_id_tab0_9_tabnameTool(cfg),
		tools_sectionkey.CreateGet_sectionkey_Tool(cfg),
		tools_sectionkey.CreateGet_sectionkeyTool(cfg),
		tools_id.CreateGet_id_collectionfield0_9__addTool(cfg),
		tools_id.CreatePost_id_collectionfield0_9__addTool(cfg),
		tools_resetpassword.CreateGet_resetpasswordTool(cfg),
		tools_resetpassword.CreatePost_resetpasswordTool(cfg),
		tools_api.CreateGetTool(cfg),
		tools_collectionfield.CreateGet_collectionfield__id_viewTool(cfg),
		tools_propertyname.CreateGet_propertyname_dynamicformTool(cfg),
		tools_selectize.CreateGet_selectizeTool(cfg),
		tools_update.CreateGet_updateTool(cfg),
		tools_update.CreatePost_updateTool(cfg),
		tools_viewphone.CreateGet_viewphoneTool(cfg),
		tools_sendresetpassword.CreatePost_sendresetpasswordTool(cfg),
		tools_deletephone.CreateGet_deletephoneTool(cfg),
		tools_addlsectionkey.CreateGet_addlsectionkey_id_chooseassetTool(cfg),
		tools_sc_conflict.CreateGet_sc_conflictTool(cfg),
		tools_collectionfield.CreateGet_collectionfield__selectTool(cfg),
		tools_id.CreateGet_id_collectionfield0_9__selectizeTool(cfg),
	}
}
