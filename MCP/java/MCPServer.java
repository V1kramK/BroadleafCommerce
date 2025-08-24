/**
 * Main MCP Server - Handles MCP JSON-RPC protocol
 */

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.concurrent.CompletableFuture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

// Import Post_Change_PasswordMCPTool - included in same package
// Import Get_Change_PasswordMCPTool - included in same package
// Import Get_Product_Product_Id_Skus_Field_Name_Generate_SkusMCPTool - included in same package
// Import Get_Sc_ConflictMCPTool - included in same package
// Import Get_Addl_Section_Key_Id_Choose_AssetMCPTool - included in same package
// Import Get_Addl_Section_Key_Id_Upload_AssetMCPTool - included in same package
// Import Get_Make_Phone_DefaultMCPTool - included in same package
// Import Get_Register_Customer_SuccessMCPTool - included in same package
// Import Post_DeleteMCPTool - included in same package
// Import Post_Forgot_UsernameMCPTool - included in same package
// Import Get_Forgot_UsernameMCPTool - included in same package
// Import Get_Delete_PhoneMCPTool - included in same package
// Import Post_Upload_AssetMCPTool - included in same package
// Import Get_Property_Name_Dynamic_FormMCPTool - included in same package
// Import Get_Update_NavigationMCPTool - included in same package
// Import Get_Register_CustomerMCPTool - included in same package
// Import Get_Session_Timer_ResetMCPTool - included in same package
// Import Get_Com_Broadleafcommerce_Inventory_Advanced_Domain_Inventory_ImplMCPTool - included in same package
// Import Post_UpdateMCPTool - included in same package
// Import Get_UpdateMCPTool - included in same package
// Import GetMCPTool - included in same package
// Import Get_LoginMCPTool - included in same package
// Import Post_Log_Java_Script_ErrorMCPTool - included in same package
// Import Get_Save_PhoneMCPTool - included in same package
// Import Get_SelectizeMCPTool - included in same package
// Import Get_Owning_Class_Collection_Field_SelectMCPTool - included in same package
// Import Get_Owning_Class_Collection_Field_TypeaheadMCPTool - included in same package
// Import Get_Forgot_PasswordMCPTool - included in same package
// Import Post_Reset_PasswordMCPTool - included in same package
// Import Get_Reset_PasswordMCPTool - included in same package
// Import Post_AddMCPTool - included in same package
// Import Get_AddMCPTool - included in same package
// Import Post_IdMCPTool - included in same package
// Import Get_Id_Collection_Field_0_9_Collection_Item_IdMCPTool - included in same package
// Import Post_Id_Collection_Field_0_9_Collection_Item_Id_DeleteMCPTool - included in same package
// Import Post_Id_Collection_Field_0_9_AddMCPTool - included in same package
// Import Post_Id_Collection_Field_0_9_Add_EmptyMCPTool - included in same package
// Import Post_Id_Collection_Field_0_9_Add_Collection_Item_Id_VerifyMCPTool - included in same package
// Import Post_Id_DuplicateMCPTool - included in same package
// Import Post_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_IdMCPTool - included in same package
// Import Get_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_Id_ViewMCPTool - included in same package
// Import Post_Id_Tab_0_9_Tab_NameMCPTool - included in same package
// Import Post_Id_Collection_Field_0_9_Collection_Item_IdMCPTool - included in same package
// Import Get_IdMCPTool - included in same package
// Import Post_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_Id_DeleteMCPTool - included in same package
// Import Get_Id_Collection_Field_0_9MCPTool - included in same package
// Import Get_Id_Collection_Field_0_9_Collection_Item_Id_ViewMCPTool - included in same package
// Import Get_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_IdMCPTool - included in same package
// Import Post_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_Id_SequenceMCPTool - included in same package
// Import Post_Id_Upload_AssetMCPTool - included in same package
// Import Get_Id_Collection_Field_0_9_AddMCPTool - included in same package
// Import Post_Id_Collection_Field_0_9_Selectize_AddMCPTool - included in same package
// Import Get_Id_Choose_AssetMCPTool - included in same package
// Import Get_Id_Collection_Field_AddMCPTool - included in same package
// Import Post_Id_Collection_Field_0_9_Collection_Item_Id_SequenceMCPTool - included in same package
// Import Get_Id_Collection_Field_0_9_SelectizeMCPTool - included in same package
// Import Post_Id_DeleteMCPTool - included in same package
// Import Get_Collection_Field_SelectMCPTool - included in same package
// Import Get_Collection_Field_DetailsMCPTool - included in same package
// Import Get_Collection_Field_Id_ViewMCPTool - included in same package
// Import Post_Send_Reset_PasswordMCPTool - included in same package
// Import Get_View_PhoneMCPTool - included in same package
// Import Get_Section_KeyMCPTool - included in same package
// Import Get_Section_KeyMCPTool - included in same package
// Import Get_TranslationMCPTool - included in same package
// Import Get_Myaccount_PhoneMCPTool - included in same package

public class MCPServer {
    
    // Common classes that all tool classes use
    public static class APIConfig {
        private final String baseUrl;
        private final String apiKey;
        
        public APIConfig(String baseUrl, String apiKey) {
            this.baseUrl = baseUrl;
            this.apiKey = apiKey;
        }
        
        public String getBaseUrl() { return baseUrl; }
        public String getApiKey() { return apiKey; }
    }
    
    public static class MCPRequest {
        private Map<String, Object> params;
        
        public Map<String, Object> getParams() { return params; }
        public void setParams(Map<String, Object> params) { this.params = params; }
        
        @SuppressWarnings("unchecked")
        public Map<String, Object> getArguments() {
            if (params != null && params.containsKey("arguments")) {
                return (Map<String, Object>) params.get("arguments");
            }
            return new HashMap<>();
        }
    }
    
    public static class MCPToolResult {
        private final String content;
        private final boolean isError;
        
        public MCPToolResult(String content, boolean isError) {
            this.content = content;
            this.isError = isError;
        }
        
        public MCPToolResult(String content) {
            this(content, false);
        }
        
        public String getContent() { return content; }
        public boolean isError() { return isError; }
    }
    
    public static class ToolDefinition {
        private final String name;
        private final String description;
        private final Map<String, Object> parameters;
        
        public ToolDefinition(String name, String description, Map<String, Object> parameters) {
            this.name = name;
            this.description = description;
            this.parameters = parameters;
        }
        
        public String getName() { return name; }
        public String getDescription() { return description; }
        public Map<String, Object> getParameters() { return parameters; }
    }
    
    public static class Tool {
        private final ToolDefinition definition;
        private final Function<MCPRequest, MCPToolResult> handler;
        
        public Tool(ToolDefinition definition, Function<MCPRequest, MCPToolResult> handler) {
            this.definition = definition;
            this.handler = handler;
        }
        
        public ToolDefinition getDefinition() { return definition; }
        public Function<MCPRequest, MCPToolResult> getHandler() { return handler; }
    }
    
    private static final ObjectMapper mapper = new ObjectMapper();
    private static List<Tool> tools = new ArrayList<>();
    private static APIConfig config;
    
    public static void main(String[] args) {
        try {
            // Initialize configuration
            String baseUrl = System.getenv("API_BASE_URL");
            String apiKey = System.getenv("API_KEY");
            
            if (baseUrl == null || apiKey == null) {
                System.err.println("Error: Please set API_BASE_URL and API_KEY environment variables");
                System.exit(1);
            }
            
            config = new APIConfig(baseUrl, apiKey);
            
            // Register all tools
            tools = Arrays.asList(
            Post_Change_PasswordMCPTool.createPost_Change_PasswordTool(config),
            Get_Change_PasswordMCPTool.createGet_Change_PasswordTool(config),
            Get_Product_Product_Id_Skus_Field_Name_Generate_SkusMCPTool.createGet_Product_Product_Id_Skus_Field_Name_Generate_SkusTool(config),
            Get_Sc_ConflictMCPTool.createGet_Sc_ConflictTool(config),
            Get_Addl_Section_Key_Id_Choose_AssetMCPTool.createGet_Addl_Section_Key_Id_Choose_AssetTool(config),
            Get_Addl_Section_Key_Id_Upload_AssetMCPTool.createGet_Addl_Section_Key_Id_Upload_AssetTool(config),
            Get_Make_Phone_DefaultMCPTool.createGet_Make_Phone_DefaultTool(config),
            Get_Register_Customer_SuccessMCPTool.createGet_Register_Customer_SuccessTool(config),
            Post_DeleteMCPTool.createPost_DeleteTool(config),
            Post_Forgot_UsernameMCPTool.createPost_Forgot_UsernameTool(config),
            Get_Forgot_UsernameMCPTool.createGet_Forgot_UsernameTool(config),
            Get_Delete_PhoneMCPTool.createGet_Delete_PhoneTool(config),
            Post_Upload_AssetMCPTool.createPost_Upload_AssetTool(config),
            Get_Property_Name_Dynamic_FormMCPTool.createGet_Property_Name_Dynamic_FormTool(config),
            Get_Update_NavigationMCPTool.createGet_Update_NavigationTool(config),
            Get_Register_CustomerMCPTool.createGet_Register_CustomerTool(config),
            Get_Session_Timer_ResetMCPTool.createGet_Session_Timer_ResetTool(config),
            Get_Com_Broadleafcommerce_Inventory_Advanced_Domain_Inventory_ImplMCPTool.createGet_Com_Broadleafcommerce_Inventory_Advanced_Domain_Inventory_ImplTool(config),
            Post_UpdateMCPTool.createPost_UpdateTool(config),
            Get_UpdateMCPTool.createGet_UpdateTool(config),
            GetMCPTool.createGetTool(config),
            Get_LoginMCPTool.createGet_LoginTool(config),
            Post_Log_Java_Script_ErrorMCPTool.createPost_Log_Java_Script_ErrorTool(config),
            Get_Save_PhoneMCPTool.createGet_Save_PhoneTool(config),
            Get_SelectizeMCPTool.createGet_SelectizeTool(config),
            Get_Owning_Class_Collection_Field_SelectMCPTool.createGet_Owning_Class_Collection_Field_SelectTool(config),
            Get_Owning_Class_Collection_Field_TypeaheadMCPTool.createGet_Owning_Class_Collection_Field_TypeaheadTool(config),
            Get_Forgot_PasswordMCPTool.createGet_Forgot_PasswordTool(config),
            Post_Reset_PasswordMCPTool.createPost_Reset_PasswordTool(config),
            Get_Reset_PasswordMCPTool.createGet_Reset_PasswordTool(config),
            Post_AddMCPTool.createPost_AddTool(config),
            Get_AddMCPTool.createGet_AddTool(config),
            Post_IdMCPTool.createPost_IdTool(config),
            Get_Id_Collection_Field_0_9_Collection_Item_IdMCPTool.createGet_Id_Collection_Field_0_9_Collection_Item_IdTool(config),
            Post_Id_Collection_Field_0_9_Collection_Item_Id_DeleteMCPTool.createPost_Id_Collection_Field_0_9_Collection_Item_Id_DeleteTool(config),
            Post_Id_Collection_Field_0_9_AddMCPTool.createPost_Id_Collection_Field_0_9_AddTool(config),
            Post_Id_Collection_Field_0_9_Add_EmptyMCPTool.createPost_Id_Collection_Field_0_9_Add_EmptyTool(config),
            Post_Id_Collection_Field_0_9_Add_Collection_Item_Id_VerifyMCPTool.createPost_Id_Collection_Field_0_9_Add_Collection_Item_Id_VerifyTool(config),
            Post_Id_DuplicateMCPTool.createPost_Id_DuplicateTool(config),
            Post_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_IdMCPTool.createPost_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_IdTool(config),
            Get_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_Id_ViewMCPTool.createGet_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_Id_ViewTool(config),
            Post_Id_Tab_0_9_Tab_NameMCPTool.createPost_Id_Tab_0_9_Tab_NameTool(config),
            Post_Id_Collection_Field_0_9_Collection_Item_IdMCPTool.createPost_Id_Collection_Field_0_9_Collection_Item_IdTool(config),
            Get_IdMCPTool.createGet_IdTool(config),
            Post_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_Id_DeleteMCPTool.createPost_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_Id_DeleteTool(config),
            Get_Id_Collection_Field_0_9MCPTool.createGet_Id_Collection_Field_0_9Tool(config),
            Get_Id_Collection_Field_0_9_Collection_Item_Id_ViewMCPTool.createGet_Id_Collection_Field_0_9_Collection_Item_Id_ViewTool(config),
            Get_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_IdMCPTool.createGet_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_IdTool(config),
            Post_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_Id_SequenceMCPTool.createPost_Id_Collection_Field_0_9_Collection_Item_Id_Alternate_Id_SequenceTool(config),
            Post_Id_Upload_AssetMCPTool.createPost_Id_Upload_AssetTool(config),
            Get_Id_Collection_Field_0_9_AddMCPTool.createGet_Id_Collection_Field_0_9_AddTool(config),
            Post_Id_Collection_Field_0_9_Selectize_AddMCPTool.createPost_Id_Collection_Field_0_9_Selectize_AddTool(config),
            Get_Id_Choose_AssetMCPTool.createGet_Id_Choose_AssetTool(config),
            Get_Id_Collection_Field_AddMCPTool.createGet_Id_Collection_Field_AddTool(config),
            Post_Id_Collection_Field_0_9_Collection_Item_Id_SequenceMCPTool.createPost_Id_Collection_Field_0_9_Collection_Item_Id_SequenceTool(config),
            Get_Id_Collection_Field_0_9_SelectizeMCPTool.createGet_Id_Collection_Field_0_9_SelectizeTool(config),
            Post_Id_DeleteMCPTool.createPost_Id_DeleteTool(config),
            Get_Collection_Field_SelectMCPTool.createGet_Collection_Field_SelectTool(config),
            Get_Collection_Field_DetailsMCPTool.createGet_Collection_Field_DetailsTool(config),
            Get_Collection_Field_Id_ViewMCPTool.createGet_Collection_Field_Id_ViewTool(config),
            Post_Send_Reset_PasswordMCPTool.createPost_Send_Reset_PasswordTool(config),
            Get_View_PhoneMCPTool.createGet_View_PhoneTool(config),
            Get_Section_KeyMCPTool.createGet_Section_KeyTool(config),
            Get_Section_KeyMCPTool.createGet_Section_KeyTool(config),
            Get_TranslationMCPTool.createGet_TranslationTool(config),
            Get_Myaccount_PhoneMCPTool.createGet_Myaccount_PhoneTool(config)
            );
            
            System.err.println("MCP Server started with " + tools.size() + " tools");
            
            // Start JSON-RPC server
            runServer();
            
        } catch (Exception e) {
            System.err.println("Failed to start MCP server: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static void runServer() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        
        while ((line = reader.readLine()) != null) {
            JsonNode request = null;
            try {
                request = mapper.readTree(line);
                JsonNode response = handleRequest(request);
                
                if (response != null) {
                    System.out.println(mapper.writeValueAsString(response));
                }
                
            } catch (Exception e) {
                // Send error response
                ObjectNode errorResponse = mapper.createObjectNode();
                errorResponse.put("jsonrpc", "2.0");
                if (request != null && request.has("id")) {
                    errorResponse.put("id", request.get("id").asText());
                } else {
                    errorResponse.putNull("id");
                }
                
                ObjectNode error = mapper.createObjectNode();
                error.put("code", -32603);
                error.put("message", "Internal error: " + e.getMessage());
                errorResponse.set("error", error);
                
                System.out.println(mapper.writeValueAsString(errorResponse));
            }
        }
    }
    
    private static JsonNode handleRequest(JsonNode request) {
        if (!request.has("method")) {
            return createErrorResponse(request, -32600, "Invalid Request");
        }
        
        String method = request.get("method").asText();
        JsonNode params = request.get("params");
        String id = request.has("id") ? request.get("id").asText() : null;
        
        switch (method) {
            case "initialize":
                return handleInitialize(id);
            case "tools/list":
                return handleToolsList(id);
            case "tools/call":
                return handleToolsCall(id, params);
            default:
                return createErrorResponse(request, -32601, "Method not found");
        }
    }
    
    private static JsonNode handleInitialize(String id) {
        ObjectNode response = mapper.createObjectNode();
        response.put("jsonrpc", "2.0");
        response.put("id", id);
        
        ObjectNode result = mapper.createObjectNode();
        result.put("protocolVersion", "2024-11-05");
        
        ObjectNode capabilities = mapper.createObjectNode();
        ObjectNode toolsCapability = mapper.createObjectNode();
        toolsCapability.put("listChanged", false);
        capabilities.set("tools", toolsCapability);
        result.set("capabilities", capabilities);
        
        ObjectNode serverInfo = mapper.createObjectNode();
        serverInfo.put("name", "Opsera MCP Server");
        serverInfo.put("version", "1.0.0");
        result.set("serverInfo", serverInfo);
        
        response.set("result", result);
        return response;
    }
    
    private static JsonNode handleToolsList(String id) {
        ObjectNode response = mapper.createObjectNode();
        response.put("jsonrpc", "2.0");
        response.put("id", id);
        
        ObjectNode result = mapper.createObjectNode();
        ArrayNode toolsArray = mapper.createArrayNode();
        
        for (Tool tool : tools) {
            ObjectNode toolDef = mapper.createObjectNode();
            toolDef.put("name", tool.getDefinition().getName());
            toolDef.put("description", tool.getDefinition().getDescription());
            
            // Convert parameters to JSON
            ObjectNode inputSchema = mapper.valueToTree(tool.getDefinition().getParameters());
            toolDef.set("inputSchema", inputSchema);
            
            toolsArray.add(toolDef);
        }
        
        result.set("tools", toolsArray);
        response.set("result", result);
        return response;
    }
    
    private static JsonNode handleToolsCall(String id, JsonNode params) {
        if (!params.has("name")) {
            return createErrorResponse(null, -32602, "Invalid params: missing 'name'");
        }
        
        String toolName = params.get("name").asText();
        JsonNode arguments = params.has("arguments") ? params.get("arguments") : mapper.createObjectNode();
        
        // Find the tool
        Tool tool = null;
        for (Tool t : tools) {
            if (t.getDefinition().getName().equals(toolName)) {
                tool = t;
                break;
            }
        }
        
        if (tool == null) {
            return createErrorResponse(null, -32602, "Tool not found: " + toolName);
        }
        
        try {
            // Convert arguments to Map
            Map<String, Object> argsMap = mapper.convertValue(arguments, Map.class);
            
            // Create MCP request
            MCPRequest mcpRequest = new MCPRequest();
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("arguments", argsMap);
            mcpRequest.setParams(requestParams);
            
            // Execute tool
            MCPToolResult result = tool.getHandler().apply(mcpRequest);
            
            // Create response
            ObjectNode response = mapper.createObjectNode();
            response.put("jsonrpc", "2.0");
            response.put("id", id);
            
            ObjectNode resultObj = mapper.createObjectNode();
            ArrayNode content = mapper.createArrayNode();
            
            ObjectNode textContent = mapper.createObjectNode();
            textContent.put("type", "text");
            textContent.put("text", result.getContent());
            content.add(textContent);
            
            resultObj.set("content", content);
            resultObj.put("isError", result.isError());
            
            response.set("result", resultObj);
            return response;
            
        } catch (Exception e) {
            return createErrorResponse(null, -32603, "Tool execution failed: " + e.getMessage());
        }
    }
    
    private static JsonNode createErrorResponse(JsonNode request, int code, String message) {
        ObjectNode response = mapper.createObjectNode();
        response.put("jsonrpc", "2.0");
        response.put("id", request != null && request.has("id") ? request.get("id").asText() : null);
        
        ObjectNode error = mapper.createObjectNode();
        error.put("code", code);
        error.put("message", message);
        response.set("error", error);
        
        return response;
    }
}