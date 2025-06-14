import ipaddress
import math

def ip_to_binary(ip):
    return ''.join(f'{int(octet):08b}' for octet in ip.split('.'))

def binary_to_ip(binary):
    return '.'.join(str(int(binary[i:i+8], 2)) for i in range(0, 32, 8))

def cidr_to_netmask(cidr):
    return str(ipaddress.IPv4Network(f'0.0.0.0/{cidr}').netmask)

def netmask_to_cidr(netmask):
    return ipaddress.IPv4Network(f'0.0.0.0/{netmask}').prefixlen

def calculate_lans(ip_network, num_lans):
    new_prefix = ip_network.prefixlen + math.ceil(math.log2(num_lans))
    if new_prefix > 30:
        raise ValueError("Too many LANs for given network size.")
    return list(ip_network.subnets(new_prefix=new_prefix))

def show_lan_details(subnets):
    for i, subnet in enumerate(subnets):
        hosts = list(subnet.hosts())
        print(f"\nLAN {i + 1}")
        print(f"  Network Address : {subnet.network_address}")
        print(f"  Broadcast Addr  : {subnet.broadcast_address}")
        print(f"  Usable IPs      : {hosts[0]} - {hosts[-1]}" if hosts else "  Usable IPs      : None")
        print(f"  CIDR Notation   : /{subnet.prefixlen}")
        print(f"  Subnet Mask     : {subnet.netmask}")

def bitwise_operation(ip, mask, operation):
    ip_bin = ip_to_binary(ip)
    mask_bin = ip_to_binary(mask)
    if operation == 'AND':
        result_bin = ''.join('1' if ip_bin[i] == '1' and mask_bin[i] == '1' else '0' for i in range(32))
    elif operation == 'OR':
        result_bin = ''.join('1' if ip_bin[i] == '1' or mask_bin[i] == '1' else '0' for i in range(32))
    else:
        raise ValueError("Invalid operation. Choose 'AND' or 'OR'.")
    return result_bin, binary_to_ip(result_bin)

def parse_input(ip_input):
    ip_input = ip_input.strip()
    if '/' in ip_input:
        return ipaddress.IPv4Network(ip_input, strict=False)
    elif ',' in ip_input:
        ip, mask = map(str.strip, ip_input.split(','))
        cidr = netmask_to_cidr(mask)
        return ipaddress.IPv4Network(f"{ip}/{cidr}", strict=False)
    else:
        return ipaddress.IPv4Network(f"{ip_input}/24", strict=False)  # default CIDR

def main():
    print("=== IP Networking Tool ===")
    ip_input = input("Enter IP (formats: IP/mask, IP/CIDR, IP only): ")

    try:
        network = parse_input(ip_input)
        print(f"Parsed Network: {network} (Mask: {network.netmask})")

        operation = input("Perform bitwise operation? (AND/OR/N): ").strip().upper()
        if operation in ['AND', 'OR']:
            result_bin, result_ip = bitwise_operation(str(network.network_address), str(network.netmask), operation)
            print(f"  Binary Result: {result_bin}")
            print(f"  Resulting IP: {result_ip}")

        mode = input("LAN Mode? (M for Manual, A for Auto): ").strip().upper()

        if mode == 'M':
            num_lans = int(input("Enter number of LANs to create: "))
            subnets = calculate_lans(network, num_lans)
            show_lan_details(subnets)
        elif mode == 'A':
            max_subnets = 2 ** (32 - network.prefixlen)
            max_possible_lans = 2 ** (30 - network.prefixlen)
            print(f"\nAuto subnetting for network: {network}")
            print(f"Max usable LANs (using /30 subnets): {max_possible_lans}")
            subnets = list(network.subnets(new_prefix=30))[:max_possible_lans]
            show_lan_details(subnets)
        else:
            print("Skipping LAN subdivision.")

    except ValueError as e:
        print(f"Error: {e}")
    except Exception:
        print("Invalid input or internal error occurred.")

if __name__ == "__main__":
    main()
