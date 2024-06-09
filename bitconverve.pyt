def ip_to_binary(ip):
    return ''.join(f'{int(octet):08b}' for octet in ip.split('.'))

def binary_to_ip(binary):
    return '.'.join(str(int(binary[i:i+8], 2)) for i in range(0, 32, 8))

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

def main():
    user_input = input("Enter IP address and subnet mask separated by a comma (e.g., 192.168.1.10,255.255.255.0): ")
    ip, mask = map(str.strip, user_input.split(','))

    operation = input("Enter the bitwise operation (AND/OR): ").strip().upper()
    
    try:
        result_bin, result_ip = bitwise_operation(ip, mask, operation)
        print(f"Binary result: {result_bin}")
        print(f"IP address result: {result_ip}")
    except ValueError as e:
        print(e)

if __name__ == "__main__":
    main()
