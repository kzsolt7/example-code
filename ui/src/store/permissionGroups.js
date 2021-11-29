import {api} from "@/api";

export const permissionGroups = {
    state: {
        roleItems: [
            // {name: "Set first sampling, reference, limit", value: "setFirstSampRefLimit"},
            // {name: "Set ref default", value: "setRefDefault"},
            // {name: "Import reference by hand", value: "importRefByHand"},
            // {name: "Set sampling time", value: "setSampTime"},
            // {name: "Create, edit, reference", value: "createEditRef"},
            // {name: "Edit Pfill, Tcool", value: "editPfillTcool"},
            // {name: "Edit evaluation value settings", value: "editEvaluationValueSettings"},
            // {name: "Edit limits", value: "editLimits"},
            // {name: "Turn sensor on/off", value: "turnSensorsOnOff"},
            // {name: "Enable/disable cycle stop", value: "enableDisableCycleStop"},
            // {name: "Edit cycle stop condition", value: "editCycleStopCond"},
            // {name: "Enable/disable switchover", value: "enableDisableSwitchover"},
            // {name: "Edit switchover condition settings", value: "editSwitchoverCondSettings"},
            // {name: "Enable/disable partial select", value: "enableDisablePartialSelect"},
            // {name: "Edit partial select", value: "editPartialSelect"},
            // {name: "Edit selector signal length", value: "editSelectorSignalLength"},
            // {name: "Enable/disable surveillance", value: "enableDisableSurveillance"},
            // {name: "Edit mold manager settings", value: "editMoldManagerSettings"},
            // {name: "Manage users", value: "manageUsers"},
            // {name: "Edit/set IMM", value: "editSetIMM"},
            // {name: "Update software", value: "updateSoftware"},
            // {name: "Edit network settings", value: "editNetworkSettings"},
            // {name: "Edit device default settings", value: "editDeviceDefaultSettings"}
        ],
        permissionGroups: ["Operator", "Engineer", "Machine adjuster"]

    },
    getters: {
        getRoleItems: state => state.roleItems,
        getPermissionGroups: state => state.permissionGroups
    },
    mutations: {
        setRoleItems(state, roles) {
            state.roleItems = roles

        }
    },
    actions: {
        getPermissions(context) {
            api.get("/user/permissions").then(r => {
                context.commit("setRoleItems",r.data)
                //console.log(context.getters.getRoleItems)
            })
        }
    },
}