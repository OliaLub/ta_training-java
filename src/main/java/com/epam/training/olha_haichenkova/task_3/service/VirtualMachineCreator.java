package com.epam.training.olha_haichenkova.task_3.service;

import com.epam.training.olha_haichenkova.task_3.model.VirtualMachine;

public class VirtualMachineCreator {
    private static final String OPERATING_SYSTEM_SOFTWARE = "free";
    private static final String VM_FAMILY = "gp";
    private static final String VM_SERIES = "n1";
    private static final String MACHINE_TYPE = "CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8";
    private static final String NUMBER_OF_GPU = "1";
    private static final String GPU_TYPE = "NVIDIA_TESLA_V100";
    private static final String LOCAL_SSD = "2";
    private static final String DATACENTER_LOCATION = "europe-west3";

    public static VirtualMachine withCharacteristics(){
        return new VirtualMachine(OPERATING_SYSTEM_SOFTWARE,
                                  VM_FAMILY,
                                  VM_SERIES,
                                  MACHINE_TYPE,
                                  NUMBER_OF_GPU,
                                  GPU_TYPE,
                                  LOCAL_SSD,
                                  DATACENTER_LOCATION);
    }

}
